package weaponPackage;

import data.Entity;
import data.GameData;
import data.World;
import data.entityparts.PositionPart;
import services.PluginService;

public class WeaponPlugin implements PluginService {
    private Entity weapon;

    private Entity createWeapon(GameData data){
       Entity weapon = new Weapon();
       return weapon;
    }

    @Override
    public void onEnable(GameData data, World world) {
        weapon = createWeapon(data);
        world.addEntity(weapon);
    }

    @Override
    public void onDisable(GameData data, World world) {
        world.removeEntity(weapon);
    }
}
