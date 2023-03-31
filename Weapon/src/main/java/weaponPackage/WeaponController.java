package weaponPackage;


import bulletpackage.Bullet;
import data.Entity;
import data.GameData;
import data.ProcessAt;
import data.World;
import services.EntityProcessingService;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;


public class WeaponController implements EntityProcessingService {
    SpriteBatch spriteBatch = new SpriteBatch();
    Texture texture = new Texture(Gdx.files.internal("../InVasion/Weapon/src/main/resources/images/hund2.png"));

    @Override
    public void process(GameData data, World world) {

    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity weapon : world.getEntities(Bullet.class)) {
            //temporary solution
            //TODO: Change this
            /*if (data.getKeys().isDown(GameKeys.SPACE)) {
                for (BulletSPI bullet : getBulletSPIs()) {
                    world.addEntity(bullet.createBullet(player, gameData));
                }
            }*/
            updateShape();
        }
    }

    private void  updateShape(){
        spriteBatch.begin();
        spriteBatch.draw(texture, 1000, 200);
        spriteBatch.end();
    }


}
