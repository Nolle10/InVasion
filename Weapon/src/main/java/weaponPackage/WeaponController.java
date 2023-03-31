package weaponPackage;


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
        updateShape();

    }

    private void  updateShape(){
        spriteBatch.begin();
        spriteBatch.draw(texture, 1000, 200);
        spriteBatch.end();
    }


}
