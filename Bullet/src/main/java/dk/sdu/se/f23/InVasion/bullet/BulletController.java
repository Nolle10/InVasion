package dk.sdu.se.f23.InVasion.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MovingPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.TimerPart;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;
import dk.sdu.se.f23.InVasion.common.events.EventListener;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.common.events.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.commonbullet.Bullet;

public class BulletController implements EntityProcessingService, EventListener {
    private GameData gameData = new GameData();
    private GameStateEnum lastKnownState;

    public BulletController() {
    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            TimerPart timerPart = bullet.getPart(TimerPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            if (timerPart.getDuration() < 0) {
                world.removeEntity(bullet);
            }

            timerPart.process(data, bullet);
            positionPart.process(data, bullet);
            movingPart.process(data, bullet);
            if (timerPart.getDuration() < 0) {
                world.removeEntity(bullet);
            }

            updateShape(bullet, data);
        }
    }

    private void updateShape(Entity bullet, GameData data) {
        PositionPart bulletPos = bullet.getPart(PositionPart.class);
        MovingPart bulletMoving = bullet.getPart(MovingPart.class);

        bulletMoving.process(data,bullet);
        float x = bulletPos.getX();
        float y = bulletPos.getY();


        data.getSpriteBatch().draw(bullet.getTexture(), x, y);
    }

    public Entity createBullet(Entity shooter, Point direction) {
        PositionPart shooterPos = shooter.getPart(PositionPart.class);

        float shooterPosX = shooterPos.getPos().getX();
        float shooterPosY = shooterPos.getPos().getY();
        float radians = shooterPos.getRadians();
        Entity bullet = new Bullet();

        bullet.add(new PositionPart(new Point((int) shooterPosX, (int) shooterPosY), radians));
        bullet.add(new MovingPart(direction, 100, 100));
        bullet.add(new LifePart(1));
        bullet.add(new TimerPart(3));
        bullet.setTexture(new Texture(Gdx.files.internal("Bullet/src/main/resources/antibody.png")));
        return bullet;
    }

    @Override
    public void processEvent(Event event, World world) {
        if (event instanceof StateChangeEvent){
            this.lastKnownState = ((StateChangeEvent)event).getNewState();
        }

        if (lastKnownState != GameStateEnum.PlayState) {
            for (Entity bullet : world.getEntities(Bullet.class)) {
                world.removeEntity(bullet);
            }
            return;
        }
        if (event instanceof FireShotEvent) {
            world.addEntity(createBullet(((FireShotEvent) event).getSource(),((FireShotEvent) event).getDirection()));
        }
    }
}
