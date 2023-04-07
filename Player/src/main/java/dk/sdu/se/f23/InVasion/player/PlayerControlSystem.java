package dk.sdu.se.f23.InVasion.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.ProcessAt;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;

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
