package bulletpackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.ProcessAt;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MovingPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.TimerPart;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

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
            TimerPart timerPart = bullet.getPart(TimerPart.class);
            movingPart.setUp(true);
            if (timerPart.getExpiration() < 0) {
                world.removeEntity(bullet);
            }

            timerPart.process(data, bullet);
            movingPart.process(data, bullet);
            positionPart.process(data, bullet);

            updateShape(bullet);
        }
    }

    private void updateShape(Entity entity) {
        entity.setTexture(new Texture(Gdx.files.internal("resources/Small-dark-green-circle.svg.png")));
        SpriteBatch spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        spriteBatch.draw(entity.getTexture(), 1000, 200);
        spriteBatch.end();
    }


    @Override
    public Entity createBullet(Entity e, GameData gameData) {
        Entity bullet = new Bullet();
        return bullet;
    }
}
