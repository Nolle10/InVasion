package dk.sdu.se.f23.InVasion.enemy;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.Entity;

import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MoneyPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MovingPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.services.PluginService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyPlugin implements PluginService {
    Entity enemy;

    @Override
    public void onEnable(GameData data, World world) {
        enemy = createEnemy(data,world);
        world.addEntity(enemy);

    }

    @Override
    public void onDisable(GameData data, World world) {
        world.removeEntity(enemy);
    }

    //Randomly creates an enemy - Should be adjusted with specific enemy types
    //This specific enemy is a STUPID type enemy
    private Entity createEnemy(GameData data, World world) {
        world.setInitState(new Point(200, 300));
        float x = world.getInitState().getX();
        float y = world.getInitState().getY();
        float radians = 3.1415f / 2;
        List<Point> route = new ArrayList<>();
        route.add(new Point(10,10));
        route.add(new Point(15,10));
        route.add(new Point(20,10));
        route.add(new Point(25,10));
        route.add(new Point(30,10));
        route.add(new Point(35,10));
        route.add(new Point(40,10));
        route.add(new Point(45,10));
        route.add(new Point(50,10));
        route.add(new Point(55,10));
        route.add(new Point(60,10));
        route.add(new Point(65,10));
        route.add(new Point(70,10));
        route.add(new Point(75,10));
        route.add(new Point(80,10));
        route.add(new Point(85,10));
        route.add(new Point(90,10));
        route.add(new Point(95,10));
        route.add(new Point(100,10));
        route.add(new Point(105,10));
        route.add(new Point(110,10));
        route.add(new Point(115,10));
        route.add(new Point(120,10));
        route.add(new Point(125,10));
        route.add(new Point(130,10));
        route.add(new Point(135,10));
        route.add(new Point(140,10));
        route.add(new Point(145,10));
        route.add(new Point(150,10));
        route.add(new Point(155,10));
        route.add(new Point(160,10));
        route.add(new Point(165,10));
        route.add(new Point(170,10));
        route.add(new Point(175,10));
        route.add(new Point(180,10));
        route.add(new Point(185,10));
        route.add(new Point(190,10));
        route.add(new Point(195,10));
        route.add(new Point(200,10));

        Entity enemyShip = new Enemy(route);
        enemyShip.setTexture(new Texture(Gdx.files.internal("Enemy/src/main/resources/dk/sdu/se/f23/InVasion/enemyresources/textures/enemytest.png")));


        enemyShip.add(new PositionPart(new Point((int) x, (int) y), radians));
        enemyShip.add(new LifePart(1));
        enemyShip.add(new MoneyPart(10));

        return enemyShip;
    }
}
