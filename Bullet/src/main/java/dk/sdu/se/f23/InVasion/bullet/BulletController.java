package dk.sdu.se.f23.InVasion.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MovingPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.TimerPart;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;
import dk.sdu.se.f23.InVasion.common.events.EventListener;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.common.events.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.commonbullet.Bullet;

public class BulletController implements EntityProcessingService, EventListener {
    private GameStateEnum lastKnownState;

    public BulletController() {
        EventDistributor.addListener(StateChangeEvent.class, this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            processParts(data, bullet);

            if (((TimerPart) bullet.getPart(TimerPart.class)).getDuration() < 0) {
                world.removeEntity(bullet);
            }

            updateShape(bullet, data);
        }
    }

    private void processParts(GameData data, Entity bullet) {
        bullet.getPart(PositionPart.class).process(data, bullet);
        bullet.getPart(MovingPart.class).process(data, bullet);
        bullet.getPart(TimerPart.class).process(data, bullet);
        bullet.getPart(MovingPart.class).process(data, bullet);
    }

    private void updateShape(Entity bullet, GameData data) {
        PositionPart bulletPos = bullet.getPart(PositionPart.class);

        float x = bulletPos.getX();
        float y = bulletPos.getY();

        data.getSpriteBatch().draw(bullet.getTexture(), x, y, Gdx.graphics.getWidth() / 80f, Gdx.graphics.getHeight() / 45f);
    }

    public Entity createBullet(Entity shooter, Point direction) {
        PositionPart shooterPos = shooter.getPart(PositionPart.class);

        Entity bullet = new Bullet();

        addParts(direction, shooterPos, bullet);

        bullet.setTexture(new Texture(Gdx.files.internal("Bullet/src/main/resources/antibodyCut.png")));

        return bullet;
    }

    private void addParts(Point direction, PositionPart shooterPos, Entity bullet) {
        bullet.add(new PositionPart(shooterPos.getPos(), shooterPos.getRadians()));
        bullet.add(new MovingPart(direction, 100, 100));
        bullet.add(new LifePart(1));
        bullet.add(new TimerPart(3));
        bullet.setTexture(new Texture(Gdx.files.internal("Bullet/src/main/resources/antibodyCut.png")));
        bullet.setTexture(new Texture(Gdx.files.internal("Bullet/src/main/resources/antibodyCut.png")));
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("Bullet/src/main/resources/pew.mp3"));
        sound.play();
        return bullet;

    }

    @SuppressWarnings("unchecked")
    @Override
    public void processEvent(Event event, World world) {
        if (event instanceof StateChangeEvent stateChangeEvent) {
            this.lastKnownState = stateChangeEvent.getNewState();
        }
        if (lastKnownState != GameStateEnum.PlayState) {
            for (Entity bullet : world.getEntities(Bullet.class)) {
                world.removeEntity(bullet);
            }
            return;
        }
        if (event instanceof FireShotEvent fireShotEvent) {
            world.addEntity(createBullet(fireShotEvent.getSource(), fireShotEvent.getDirection()));
        }
    }
}
