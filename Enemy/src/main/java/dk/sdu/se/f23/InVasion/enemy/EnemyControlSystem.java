package dk.sdu.se.f23.InVasion.enemy;


import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MoneyPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.TargetEvent;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.enemy.services.ActionService;


import java.util.*;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements EntityProcessingService, EventListener{

    private MoveToAction movingAction = new MoveToAction();

    public EnemyControlSystem() {
    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            MoneyPart moneyPart = enemy.getPart(MoneyPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            EventDistributor.sendEvent(new TargetEvent(enemy,positionPart.getPos()),world);
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

    public void moveTo(Entity enemy, Point location) {
        movingAction.setPosition(location.getX(), location.getY());
        movingAction.setDuration(((Enemy)enemy).getSpeed());
    }





}
