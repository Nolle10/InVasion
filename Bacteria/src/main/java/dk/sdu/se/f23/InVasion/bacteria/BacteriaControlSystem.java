package dk.sdu.se.f23.InVasion.bacteria;

import com.badlogic.gdx.Gdx;
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

public class BacteriaControlSystem implements EntityProcessingService, EventListener {

    private static int enemiesToSpawn;
    private float timeSinceLastSpawn;
    private ActionService actionService;
    private GameStateEnum lastKnownState;


    public BacteriaControlSystem() {
        EventDistributor.addListener(StateChangeEvent.class, this);

        for (ActionService service : getActionServices()) {
            if (service.getAiType() == AIType.STUPID) {
                actionService = service;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(GameData data, World world) {
        if (lastKnownState != GameStateEnum.PlayState) {
            return;
        }
        timeSinceLastSpawn += data.getDelta();
        if (enemiesToSpawn > 0 && timeSinceLastSpawn > 1) {
            timeSinceLastSpawn = 0;
            enemiesToSpawn--;
            List<Point> route;
            if (actionService != null) {
                route = actionService.calculate(world);
            } else {
                route = List.of(world.getInitState());
            }
            Enemy enemy = new Enemy(route);
            enemy.setDamage(1);
            enemy.add(new PositionPart(route.get(0), 0));
            enemy.add(new LifePart(2));
            enemy.add(new MoneyPart(2));
            enemy.setTexture(new Texture(Gdx.files.internal("Bacteria/src/main/resources/textures/bacteria.png")));
            world.addEntity(enemy);
        }
        if (enemiesToSpawn == 0 && world.getEntities(Enemy.class).isEmpty()) {
            EventDistributor.sendEvent(new WaveIsDoneEvent(SystemSender.Module), world);
        }


        for (Entity enemy : world.getEntities(Enemy.class)) {
            MoneyPart moneyPart = enemy.getPart(MoneyPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);
            moneyPart.process(data, enemy);
            lifePart.process(data, enemy);
            updateShape(enemy, data, world);
        }
    }

    private void updateShape(Entity entity, GameData data, World world) {
        Point nextPoint = ((Enemy) entity).getNextPoint(data.getDelta(), world, entity);

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
        if (event instanceof StateChangeEvent stateChangeEvent) {
            this.lastKnownState = stateChangeEvent.getNewState();
        } else if (event instanceof SpawnEnemysEvent spawnEnemiesEvent) {
            enemiesToSpawn = spawnEnemiesEvent.getWaveLevel() * 2;
        }
    }
}
