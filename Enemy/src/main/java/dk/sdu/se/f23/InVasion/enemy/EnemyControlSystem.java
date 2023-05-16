package dk.sdu.se.f23.InVasion.enemy;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MoneyPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.events.EventListener;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;
import dk.sdu.se.f23.InVasion.common.events.events.SpawnEnemysEvent;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.commonenemy.Enemy;
import dk.sdu.se.f23.InVasion.enemy.services.ActionService;


import java.util.*;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements EntityProcessingService, EventListener {
    private static int enemiesToSpawn;
    private float timeSinceLastSpawn;
    private ActionService actionService;

    public EnemyControlSystem() {
        for (ActionService service : getActionServices()) {
            if (service.getAiType() == AIType.A_STAR){
                actionService = service;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        timeSinceLastSpawn += data.getDelta();
        if (enemiesToSpawn > 0 && timeSinceLastSpawn > 1) {
            timeSinceLastSpawn = 0;
            enemiesToSpawn--;
            List<Point> route = actionService.calculate(world);
            Enemy enemy = new Enemy(route);
            enemy.add(new PositionPart(route.get(0), 0));
            enemy.add(new LifePart(2));
            enemy.add(new MoneyPart(2));
            enemy.setTexture(new Texture(Gdx.files.internal("Enemy/src/main/resources/dk/sdu/se/f23/InVasion/enemyresources/textures/enemy2.png")));
            world.addEntity(enemy);
        }


        for (Entity enemy : world.getEntities(Enemy.class)) {
            MoneyPart moneyPart = enemy.getPart(MoneyPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);
            moneyPart.process(data, enemy);
            lifePart.process(data, enemy);
            updateShape(enemy, data);
        }
    }

    private void updateShape(Entity entity, GameData data) {
        Point nextPoint = ((Enemy) entity).getNextPoint(data.getDelta());

        float x = nextPoint.getX();
        float y = nextPoint.getY();

        PositionPart positionPart = entity.getPart(PositionPart.class);
        positionPart.setPos(nextPoint);
        data.getSpriteBatch().draw(entity.getTexture(), x, y);
    }


    //Load all ActionServices (AIs)
    private Collection<? extends ActionService> getActionServices() {
        return ServiceLoader.load(ActionService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Override
    public void processEvent(Event event, World world) {
        SpawnEnemysEvent spawnEnemiesEvent = (SpawnEnemysEvent) event;
        enemiesToSpawn = spawnEnemiesEvent.getWaveLevel() * 2;
    }
}

