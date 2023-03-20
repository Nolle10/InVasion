package playerpackage;

import data.Entity;
import data.GameData;
import data.World;
import services.PluginService;
public class PlayerPlugin implements PluginService {
    private Entity player;

    public PlayerPlugin(){

    }

    private Entity createPlayer(GameData gameData){
        float[] floats = {10,10,10,10};
        Entity playerTower = new Player();

        playerTower.setShapeX(floats);
        playerTower.setShapeY(floats);

        return playerTower;
    }

    @Override
    public void onEnable(GameData data, World world) {
        player = createPlayer(data);

        world.addEntity(player);
    }

    @Override
    public void onDisable(GameData data, World world) {
        world.removeEntity(player);
    }
}
