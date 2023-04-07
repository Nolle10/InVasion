package dk.sdu.se.f23.InVasion.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MovingPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.enemy.services.ActionService;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.exit;
import static java.lang.System.exit;
import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements EntityProcessingService {

    private Entity enemy = new Enemy();
    private int step = 0;
    float offsetX, offsetY;


    public EnemyControlSystem() {
    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);

            //TEMPORARY RANDOM MOVEMENT
            Random rand = new Random();

            float rng = rand.nextFloat();

            if (rng > 0.1f && rng < 0.9f) {
                movingPart.setUp(true);
            }

            if (rng < 0.2f) {
                movingPart.setLeft(true);
            }

            if (rng > 0.8f) {
                movingPart.setRight(true);
            }

            movingPart.process(data, enemy);
            positionPart.process(data, enemy);
            lifePart.process(data, enemy);

            updateShape(enemy);

            movingPart.setRight(false);
            movingPart.setLeft(false);
            movingPart.setUp(false);
        }

    }

    private void updateShape(Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        entity.setTexture(new Texture(Gdx.files.internal("textures/enemytest.png")));
        SpriteBatch spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        spriteBatch.draw(entity.getTexture(), x, y);
        spriteBatch.end();
    }

    //Find route from AI
    private void findRoute(Enemy enemy, GameData gameData, World world) {
        boolean found = false;
        for (ActionService actionService : getActionServices()) {
            if (actionService.getAiType() == enemy.getAIType()) {
                enemy.setRoute(actionService.calculate(gameData, world, enemy));
                found = true;
                break;
            }
        }
        if (!found) {
            //DO SOMETHING else if no AI is found
            //TODO: Find default route
        }
    }

    //Go to next step in route
    private void nextStep(Enemy entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        MovingPart movingPart = entity.getPart(MovingPart.class);
        LifePart lifePart = entity.getPart(LifePart.class);

        if (step < entity.getRoute().size()) {
            Point nextStep = entity.getRoute().get(step);
            offsetX = nextStep.getX() - positionPart.getX();
            offsetY = nextStep.getY() - positionPart.getY();
            step++;
        } else {
            //Do something when route is finished
        }
    }


    //Load all ActionServices (AIs)
    private Collection<? extends ActionService> getActionServices() {
        return ServiceLoader.load(ActionService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


    //TODO: Delete this
    @Deprecated
    @Override
    public void process(GameData data, World world) {
    }
}
