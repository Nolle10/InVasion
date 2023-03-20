package playerpackage;

import data.Entity;
import data.GameData;
import data.World;
import services.EntityProcessingService;

public class PlayerControlSystem implements EntityProcessingService {
    @Override
    public void process(GameData data, World world) {
        for(Entity player : world.getEntities(Player.class)){
            updateTower(player);
        }
    }

    private void updateTower(Entity entity){
        float[] shapex = {10,10,10,10};
        float[] shapey = {10,10,10,10};

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
