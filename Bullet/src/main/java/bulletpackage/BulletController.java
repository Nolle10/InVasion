package bulletpackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import data.Entity;
import data.GameData;
import data.ProcessAt;
import data.World;
import data.entityparts.MovingPart;
import data.entityparts.PositionPart;
import data.entityparts.TimerPart;
import services.EntityProcessingService;

public class BulletController implements EntityProcessingService {
    SpriteBatch spriteBatch = new SpriteBatch();
    Texture texture = new Texture(Gdx.files.internal("../InVasion/Bullet/src/main/resources/images/bullet.png"));


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

            setShape();
        }
    }

    private void setShape() {
        spriteBatch.begin();
        spriteBatch.draw(texture, 1000, 200);
        spriteBatch.end();
    }
}
