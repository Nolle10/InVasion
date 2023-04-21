package bulletpackage;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.services.PluginService;
public class BulletPlugin implements PluginService{
    @Override
    public void onEnable(GameData data, World world) {
        System.out.println("Enabeling shitz");
        BulletController bulletController = new BulletController(world);
        EventDistributor.addListener(FireShotEvent.class, bulletController);

        EventDistributor.sendEvent(new FireShotEvent(new Entity(), 22.00));

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
