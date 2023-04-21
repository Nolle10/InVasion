package bulletpackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MovingPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.TimerPart;
import dk.sdu.se.f23.InVasion.common.events.Event;
import dk.sdu.se.f23.InVasion.common.events.EventListener;
import dk.sdu.se.f23.InVasion.common.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.util.stream.Collectors.toList;

public class BulletController implements EntityProcessingService, EventListener {
    private World world;

    public BulletController(World world) {
        this.world = world;
    }

    @Override
    public void process(GameData data, World world) {

    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);

            movingPart.process(data, bullet);
            positionPart.process(data, bullet);
            lifePart.process(data, bullet);

            updateShape((Bullet) bullet);

            movingPart.setRight(false);
            movingPart.setLeft(false);
            movingPart.setUp(false);
        }
    }

    private void updateShape(Bullet entity) {
        PositionPart shooterPos = entity.getPart(PositionPart.class);

        float x = shooterPos.getX();
        float y = shooterPos.getY();

        Gdx.input.setInputProcessor(MyListener.getInstance());
        float mouseX = MyListener.getInstance().getMousePositionX();
        float mouseY = MyListener.getInstance().getMousePositionY();
        System.out.println("fundet" + mouseX + " " + mouseY);


        Point p = new Point((int) (mouseX - x), (int) (mouseY - y));

        SpriteBatch spriteBatch = entity.getSpriteBatch();
        spriteBatch.begin();
        spriteBatch.draw(entity.getTexture(), p.getX(), p.getY());
        spriteBatch.end();
    }

    public Entity createBullet(Entity shooter, double direction) {
        PositionPart shooterPos = shooter.getPart(PositionPart.class);

        float x = shooterPos.getX();
        float y = shooterPos.getY();
        float radians = (float) direction;//i think this is wrong
        float speed = 350;
        Gdx.input.setInputProcessor(MyListener.getInstance());
        float mouseX = MyListener.getInstance().getMousePositionX();
        float mouseY = MyListener.getInstance().getMousePositionY();

        Bullet bullet = new Bullet();

        Point p = new Point((int) (x + mouseX), (int) (y + mouseY));
        //(bx + x + by + y)
        bullet.add(new PositionPart(p, radians));
        bullet.add(new MovingPart(0, 500, speed, 5));
        bullet.add(new LifePart(1));
        bullet.setTexture(new Texture(Gdx.files.internal("star2.png")));
        SpriteBatch spriteBatch = new SpriteBatch();
        bullet.setSpriteBatch(spriteBatch);
        return bullet;
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof FireShotEvent){
            System.out.println("A bullet was fired");
            world.addEntity(createBullet(event.getSource(), ((FireShotEvent) event).getDirection()));
        }
    }
}
