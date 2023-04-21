package dk.sdu.se.f23.InVasion.bullet;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.services.PluginService;
public class BulletPlugin implements PluginService{
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
