package dk.sdu.se.f23.InVasion.enemy;


import dk.sdu.se.f23.InVasion.common.data.Entity;

import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MovingPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.services.PluginService;

import java.util.Random;

public class EnemyPlugin implements PluginService {
    Entity enemy;

    @Override
    public void onEnable(GameData data, World world) {
        enemy = createEnemy(data);
        world.addEntity(enemy);

    }

    @Override
    public void onDisable(GameData data, World world) {
        world.removeEntity(enemy);
    }

    //Randomly creates an enemy - Should be adjusted with specific enemy types
    //This specific enemy is a STUPID type enemy
    private Entity createEnemy(GameData data) {
        float deacceleration = 10;
        float acceleration = 150;
        float maxSpeed = 200;
        float rotationSpeed = 5;
        float x = new Random().nextFloat() * data.getDisplayWidth();
        float y = new Random().nextFloat() * data.getDisplayHeight();
        float radians = 3.1415f / 2;


        Entity enemyShip = new Enemy();
        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new PositionPart(new Point((int) x, (int) y), radians));
        enemyShip.add(new LifePart(1));

        return enemyShip;
    }
}
