package dk.sdu.se.f23.InVasion.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.enemy.services.ActionService;


import java.util.*;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements EntityProcessingService {

    private MoveToAction movingAction = new MoveToAction();

    public EnemyControlSystem() {
    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            updateShape(enemy, data);
        }
    }

    private void updateShape(Entity entity, GameData data) {
        Point nextPoint = ((Enemy) entity).getNextPoint(data.getDelta());

        float x = nextPoint.getX();
        float y = nextPoint.getY();

        SpriteBatch spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        spriteBatch.draw(entity.getTexture(), x, y);
        spriteBatch.end();
    }


    //Load all ActionServices (AIs)
    private Collection<? extends ActionService> getActionServices() {
        return ServiceLoader.load(ActionService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public void moveTo(Entity enemy, Point location) {
        movingAction.setActor(enemy);
        movingAction.setPosition(location.getX(), location.getY());
        movingAction.setDuration(((Enemy)enemy).getSpeed());
        enemy.addAction(movingAction);
    }

    public void stopAction(Entity enemy) {
        enemy.removeAction(movingAction);
    }

    public void stopAllActions(Entity enemy) {
        enemy.clearActions();
    }



}
