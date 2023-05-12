package dk.sdu.se.f23.InVasion.bullet;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.common.services.PluginService;
import dk.sdu.se.f23.InVasion.commonbullet.Bullet;

public class BulletPlugin implements PluginService{
    BulletController bulletController = new BulletController();

    @Override
    public void onEnable(GameData data, World world) {
        EventDistributor.addListener(FireShotEvent.class, bulletController);
        EventDistributor.addListener(StateChangeEvent.class, bulletController);
    }

    @Override
    public void onDisable(GameData data, World world) {
        EventDistributor.removeListener(FireShotEvent.class, bulletController);
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }
}
