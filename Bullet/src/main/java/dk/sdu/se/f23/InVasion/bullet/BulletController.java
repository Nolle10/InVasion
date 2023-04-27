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
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.EventListener;
import dk.sdu.se.f23.InVasion.common.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BulletController implements EntityProcessingService, EventListener {

    private List<Entity> shooters = EventDistributor.getEventActivatorMap().keySet()
            .stream()
            .filter(fs -> fs.isInstance(FireShotEvent.class))
            .map(FireShotEvent.class::cast)
            .map(fireShotEvent -> fireShotEvent.getSource())
            .collect(Collectors.toList());
    private GameData gameData = new GameData();
    public BulletController() {
    }

    @Deprecated
    @Override
    public void process(GameData data, World world) {

    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            System.out.println(positionPart.getX() + " " + positionPart.getY());
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            TimerPart timerPart = bullet.getPart(TimerPart.class);
            movingPart.setUp(true);
            if (timerPart.getExpiration() < 0) {
                world.removeEntity(bullet);
            }

            timerPart.process(data, bullet);
            movingPart.process(data, bullet);
            positionPart.process(data, bullet);

            /*for (Entity shooter : shooters) {
                updateShape(bullet, shooter);
            }*/
            updateShape(bullet);
        }

    }

    private void updateShape(Entity bullet) {
        PositionPart bulletPos = bullet.getPart(PositionPart.class);


        float x = bulletPos.getX();
        float y = bulletPos.getY();

        Gdx.input.setInputProcessor(MouseProcessor.getInstance());
        float mouseX = MouseProcessor.getInstance().getMousePositionX();
        float mouseY = MouseProcessor.getInstance().getMousePositionY();
        System.out.println("fundet" + mouseX + " " + mouseY);


        Point p = new Point((int) (mouseX - x), (int) (mouseY - y));

        bullet.getSpriteBatch().begin();
        bullet.getSpriteBatch().draw(bullet.getTexture(), p.getX(), p.getY());
        bullet.getSpriteBatch().end();
    }

    public Entity createBullet(Entity shooter) {
        PositionPart shooterPos = shooter.getPart(PositionPart.class);

        float x = shooterPos.getX();
        float y = shooterPos.getY();
        if (shooterPos == null){
            x = 400;
            y = 600;
        }
        float radians = shooterPos.getRadians();
        float speed = 350;

        //Some sort of logic that determines the type of shooter and replaces direction with the right
        //direction for the shooter
        Gdx.input.setInputProcessor(MouseProcessor.getInstance());
        float mouseX = MouseProcessor.getInstance().getMousePositionX();
        float mouseY = MouseProcessor.getInstance().getMousePositionY();

        float directionX = mouseX - shooterPos.getX();
        float directionY = mouseY - shooterPos.getY();
        float directionLength = (float) Math.sqrt(directionX * directionX + directionY * directionY);
        directionX /= directionLength;
        directionY /= directionLength;

        Bullet bullet = new Bullet();
        Point p = new Point((int) (x + mouseX), (int) (y + mouseY));
        bullet.add(new PositionPart(new Point((int) shooterPos.getX(), (int) shooterPos.getY()), radians));
        bullet.add(new MovingPart(directionX*speed, directionY*speed, speed, 0));
        bullet.add(new LifePart(1));
        bullet.add(new TimerPart(2000));
        bullet.setTexture(new Texture(Gdx.files.internal("Bullet/src/main/resources/star2.png")));
        bullet.setSpriteBatch(new SpriteBatch());
        return bullet;
    }

    @Override
    public void processEvent(Event event,World world) {
        if (event instanceof FireShotEvent) {
            System.out.println("A bullet was fired");
            world.addEntity(createBullet(event.getSource()));
            process(gameData, world, ProcessAt.Tick);
        }
    }
}
