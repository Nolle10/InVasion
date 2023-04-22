package dk.sdu.se.f23.InVasion.bullet;

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

public class BulletController implements EntityProcessingService, EventListener {
    private World world;

    public BulletController(World world) {
        this.world = world;
    }

    @Deprecated
    @Override
    public void process(GameData data, World world) {

    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            TimerPart timerPart = bullet.getPart(TimerPart.class);
            movingPart.setUp(true);
            if (timerPart.getExpiration() < 0){
                world.removeEntity(bullet);
            }

            timerPart.process(data,bullet);
            movingPart.process(data, bullet);
            positionPart.process(data, bullet);

            updateShape((Bullet) bullet);
        }

    }
    private void updateShape(Bullet entity) {
        PositionPart shooterPos = entity.getPart(PositionPart.class);

        float x = shooterPos.getX();
        float y = shooterPos.getY();

        Gdx.input.setInputProcessor(ShootListener.getInstance());
        float mouseX = ShootListener.getInstance().getMousePositionX();
        float mouseY = ShootListener.getInstance().getMousePositionY();
        System.out.println("fundet" + mouseX + " " + mouseY);


        Point p = new Point((int) (mouseX - x), (int) (mouseY - y));

        SpriteBatch spriteBatch = entity.getSpriteBatch();
        spriteBatch.begin();
        spriteBatch.draw(entity.getTexture(), p.getX(), p.getY());
        spriteBatch.end();
    }

    public Entity createBullet(Entity shooter, GameData data) {
        PositionPart shooterPos = shooter.getPart(PositionPart.class);

        float x = shooterPos.getX();
        float y = shooterPos.getY();
        float radians = shooterPos.getRadians();
        float deltaT = data.getDelta();
        float speed = 350;


        Gdx.input.setInputProcessor(ShootListener.getInstance());
        float mouseX = ShootListener.getInstance().getMousePositionX();
        float mouseY = ShootListener.getInstance().getMousePositionY();

        Bullet bullet = new Bullet();
        Point p = new Point((int) (x + mouseX), (int) (y + mouseY));
        bullet.add(new PositionPart(p, radians));
        bullet.add(new MovingPart(0, 500, speed, 5));
        bullet.add(new LifePart(1));

        return bullet;
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof FireShotEvent){
            System.out.println("A bullet was fired");
            world.addEntity(createBullet(event.getSource(), ));
        }
    }
}
