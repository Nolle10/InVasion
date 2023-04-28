package dk.sdu.se.f23.InVasion.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.TimerPart;
import dk.sdu.se.f23.InVasion.common.events.Event;
import dk.sdu.se.f23.InVasion.common.events.EventListener;
import dk.sdu.se.f23.InVasion.common.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;

public class BulletController implements EntityProcessingService, EventListener {
    private GameData gameData = new GameData();

    public BulletController() {
    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            TimerPart timerPart = bullet.getPart(TimerPart.class);
            if (timerPart.getDuration() < 0) {
                world.removeEntity(bullet);
            }

            timerPart.process(data, bullet);
            positionPart.process(data, bullet);

            updateShape(bullet, data);
        }

    }

    private void updateShape(Entity bullet, GameData data) {
        PositionPart bulletPos = bullet.getPart(PositionPart.class);
        float x = bulletPos.getX();
        float y = bulletPos.getY();

        Gdx.input.setInputProcessor(MouseProcessor.getInstance());
        float mouseX = MouseProcessor.getInstance().getMousePositionX();
        float mouseY = Gdx.graphics.getHeight() - MouseProcessor.getInstance().getMousePositionY();

        float angle = MathUtils.atan2(mouseY - y, mouseX - x) * MathUtils.radiansToDegrees;
        bulletPos.setRadians(angle - 90);
        float speed = 200;
        float dx = MathUtils.cosDeg(angle) * speed;
        float dy = MathUtils.sinDeg(angle) * speed;
        bulletPos.setX(x + dx * Gdx.graphics.getDeltaTime());
        bulletPos.setY(y + dy * Gdx.graphics.getDeltaTime());

        data.getSpriteBatch().draw(bullet.getTexture(), bulletPos.getX(), bulletPos.getY());
    }

    public Entity createBullet(Entity shooter) {
        PositionPart shooterPos = shooter.getPart(PositionPart.class);

        float shooterPosX = shooterPos.getX();
        float shooterPosY = shooterPos.getY();
        if (shooterPos == null) {
            shooterPosX = 400;
            shooterPosY = 600;
        }
        float radians = shooterPos.getRadians();
        Entity bullet = new Bullet();

        //Some sort of logic that determines the type of shooter and replaces direction with the right
        //direction for the shooter
        Gdx.input.setInputProcessor(MouseProcessor.getInstance());
        int mouseX = MouseProcessor.getInstance().getMousePositionX();
        int mouseY = MouseProcessor.getInstance().getMousePositionY();

        bullet.add(new PositionPart(new Point((int) shooterPosX + mouseX, (int) shooterPosY + mouseY), radians));
        bullet.add(new LifePart(1));
        bullet.add(new TimerPart(40));
        bullet.setTexture(new Texture(Gdx.files.internal("Bullet/src/main/resources/star2.png")));
        return bullet;
    }

    @Override
    public void processEvent(Event event, World world) {
        if (event instanceof FireShotEvent) {
            world.addEntity(createBullet(event.getSource()));
        }
    }
}
