package playerpackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import data.GameData;
import data.ProcessAt;
import data.World;
import services.EntityProcessingService;

public class PlayerControlSystem implements EntityProcessingService {
    @Override
    public void process(GameData data, World world) {

    }

    private void createPlayer(){
        SpriteBatch spriteBatch = new SpriteBatch();
        Texture texture = new Texture(Gdx.files.internal("images/tower.png"));


        spriteBatch.begin();
        spriteBatch.draw(texture, 1500, 200);
        spriteBatch.end();
    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        createPlayer();
    }
}
