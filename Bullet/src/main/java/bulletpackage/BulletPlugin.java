package bulletpackage;

import data.Entity;
import data.GameData;
import data.World;
import services.PluginService;
public class BulletPlugin implements PluginService{

    private Entity bullet;

    @Override
    public void onEnable(GameData data, World world) {

    }

    @Override
    public void onDisable(GameData data, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }
}
