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

    public List<Point> testRoute () {
        List<Point> route = new ArrayList<>();
        route.add(new Point(10,10));
        route.add(new Point(15,10));
        route.add(new Point(1000,200));
        return route;
    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            updateShape(enemy);
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);


            for (Point point : testRoute()){
                System.out.println(point.getX() +" "+ point.getY());
                moveTo(enemy,point);
                lifePart.process(data, enemy);
                updateShape(enemy);
            }
        }
    }

    private void updateShape(Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();

        entity.setTexture(new Texture(Gdx.files.internal("Enemy/src/main/resources/dk/sdu/se/f23/InVasion/enemyresources/textures/enemytest.png")));
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


    //TODO: Delete this
    @Deprecated
    @Override
    public void process(GameData data, World world) {
    }
}
