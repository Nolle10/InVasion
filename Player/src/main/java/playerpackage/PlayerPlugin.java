package playerpackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import data.Entity;
import data.GameData;
import data.World;
import services.PluginService;

public class PlayerPlugin implements PluginService {
    Entity player;

    @Override
    public void onEnable(GameData data, World world) {
        player = createPlayerInfo(data);
        world.addEntity(player);
    }

    private Entity createPlayerInfo(GameData data){
        Entity player = new Player();
        return player;
    }

    @Override
    public void onDisable(GameData data, World world) {
        world.removeEntity(player);
    }
}
