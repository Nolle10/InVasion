package bulletpackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MovingPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.TimerPart;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.util.stream.Collectors.toList;

public class BulletController implements EntityProcessingService, BulletSPI {

    @Override
    public void process(GameData data, World world) {

    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);

            /*// TODO: TEMPORARY RANDOM MOVEMENT
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
            }*/

            movingPart.process(data, bullet);
            positionPart.process(data, bullet);
            lifePart.process(data, bullet);

            updateShape(bullet);

            movingPart.setRight(false);
            movingPart.setLeft(false);
            movingPart.setUp(false);
        }
    }

    private void updateShape(Entity entity) {
        //new
        PositionPart shooterPos = entity.getPart(PositionPart.class);

        float x = shooterPos.getX();
        float y = shooterPos.getY();
        /*float radians = shooterPos.getRadians();
        float speed = 350;*/

        Gdx.input.setInputProcessor(MyListener.getInstance());
        float mouseX = MyListener.getInstance().getMousePositionX();
        float mouseY = MyListener.getInstance().getMousePositionY();
        System.out.println("fundet"+mouseX+" "+mouseY);


        Point p = new Point((int) (mouseX-x), (int) (mouseY-y));
        //old
        /*PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();

        Gdx.input.setInputProcessor(MyListener.getInstance());
        float mouseX = MyListener.getInstance().getMousePositionX();
        float mouseY = MyListener.getInstance().getMousePositionY();
        System.out.println("fundet"+mouseX+" "+mouseY);*/

        entity.setTexture(new Texture(Gdx.files.internal("star2.png")));
        SpriteBatch spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        spriteBatch.draw(entity.getTexture(), p.getX(), p.getY());
        spriteBatch.end();
    }

    public Entity createBullet(Entity e, GameData gameData) {
        PositionPart shooterPos = e.getPart(PositionPart.class);

        float x = shooterPos.getX();
        float y = shooterPos.getY();
        float radians = shooterPos.getRadians();//i think this is wrong
        float speed = 350;
        Gdx.input.setInputProcessor(MyListener.getInstance());
        float mouseX = MyListener.getInstance().getMousePositionX();
        float mouseY = MyListener.getInstance().getMousePositionY();


       /* Gdx.input.setInputProcessor(MyListener.getInstance());
        float mouseX = MyListener.getInstance().getMousePositionX();
        float mouseY = MyListener.getInstance().getMousePositionY();
        System.out.println("fundet"+mouseX+" "+mouseY);*/

        Entity bullet = new Bullet();
        //bullet.setRadius(2);

        /*int bx = (int) (cos(radians) * e.getRadius() * bullet.getRadius());
        int by = (int) (sin(radians) * e.getRadius() * bullet.getRadius());*/

        Point p = new Point((int) (x+mouseX), (int) (y+mouseY));
        //(bx + x + by + y)
        bullet.add(new PositionPart(p, radians));
        bullet.add(new MovingPart(0, 500, speed, 5));
        bullet.add(new LifePart(1));
        return bullet;
    }
}
