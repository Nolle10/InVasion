package playerpackage;

<<<<<<< HEAD
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.services.PluginService;
=======
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import data.GameData;
import data.World;
import services.PluginService;

>>>>>>> main
public class PlayerPlugin implements PluginService {
    SpriteBatch spriteBatch = new SpriteBatch();
    Texture texture = new Texture(Gdx.files.internal("../InVasion/Player/src/main/resources/images/tower.png"));


    @Override
    public void onEnable(GameData data, World world) {
        spriteBatch.begin();
        spriteBatch.draw(texture, 1000, 200);
        spriteBatch.end();
    }

    @Override
    public void onDisable(GameData data, World world) {
        //TODO: Insert a function that deletes the sprite
    }
}
