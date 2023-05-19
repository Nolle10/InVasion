package dk.sdu.se.f23.InVasion.enemy;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MoneyPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.EventListener;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.common.events.enums.SystemSender;
import dk.sdu.se.f23.InVasion.common.events.events.SpawnEnemysEvent;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.common.events.events.WaveIsDoneEvent;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.commonenemy.AIType;
import dk.sdu.se.f23.InVasion.commonenemy.Enemy;
import dk.sdu.se.f23.InVasion.commonenemy.services.ActionService;

import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements EntityProcessingService, EventListener {
    private static int enemiesToSpawn;
    private float timeSinceLastSpawn;
    private ActionService actionService;
    private GameStateEnum lastKnownState;


    public EnemyControlSystem() {
        EventDistributor.addListener(StateChangeEvent.class, this);

        for (ActionService service : getActionServices()) {
            if (service.getAiType() == AIType.A_STAR) {
                actionService = service;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        if (lastKnownState != GameStateEnum.PlayState) {
            return;
        }

        timeSinceLastSpawn += data.getDelta();
        if (enemiesToSpawn > 0 && timeSinceLastSpawn > 0.8) {
            timeSinceLastSpawn = 0;
            enemiesToSpawn--;

            Enemy enemy = buildEnemy(data, world);

            world.addEntity(enemy);
        }
        if (enemiesToSpawn == 0 && world.getEntities(Enemy.class).isEmpty()) {
            EventDistributor.sendEvent(new WaveIsDoneEvent(SystemSender.Module), world);
        }

        for (Entity enemy : world.getEntities(Enemy.class)) {
            if (processEnemyLife(data, world, enemy)) {
                continue;
            }
            updateShape(enemy, data);
        }
    }

    private static boolean processEnemyLife(GameData data, World world, Entity enemy) {
        LifePart lifePart = enemy.getPart(LifePart.class);
        lifePart.process(data, enemy);

        if (lifePart.isDead()) {
            enemy.getPart(MoneyPart.class).process(data, enemy);
            world.removeEntity(enemy);
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("Enemy/src/main/resources/dk/sdu/se/f23/InVasion/enemyresources/textures/Ou.mp3"));
            sound.play();
            return true;
        }
        return false;
    }

    private Enemy buildEnemy(GameData data, World world) {
        List<Point> route = actionService.calculate(world);


        Enemy enemy = new Enemy(route);
        addParts(data, route, enemy);
        enemy.setTexture(new Texture(Gdx.files.internal("Enemy/src/main/resources/dk/sdu/se/f23/InVasion/enemyresources/textures/enemy2.png")));

        return enemy;
    }

    private static void addParts(GameData data, List<Point> route, Enemy enemy) {
        enemy.add(new PositionPart(route.get(0), 0));
        enemy.add(new LifePart(data.getWaveCount() * 2));
        enemy.add(new MoneyPart(data.getWaveCount() * 10));
    }

    private void updateShape(Entity entity, GameData data) {
        Point nextPoint = ((Enemy) entity).getNextPoint(data.getDelta());

        ((PositionPart) entity.getPart(PositionPart.class)).setPos(nextPoint);

        data.getSpriteBatch().draw(entity.getTexture(), nextPoint.getX(), nextPoint.getY());
    }


    //Load all ActionServices (AIs)
    private Collection<? extends ActionService> getActionServices() {
        return ServiceLoader.load(ActionService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Override
    public void processEvent(Event event, World world) {
        if (event instanceof StateChangeEvent stateChangeEvent) {
            this.lastKnownState = stateChangeEvent.getNewState();
        } else if (event instanceof SpawnEnemysEvent spawnEnemiesEvent) {
            enemiesToSpawn = spawnEnemiesEvent.getWaveLevel() * 2;
        }
    }
}

