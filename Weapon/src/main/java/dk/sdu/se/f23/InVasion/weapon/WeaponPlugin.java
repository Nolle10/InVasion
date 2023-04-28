package dk.sdu.se.f23.InVasion.weapon;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.ProcessAt;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.common.services.PluginService;

public class WeaponPlugin implements PluginService {
    private String weaponName = "Steven";


    @Override
    public void onEnable(GameData data, World world) {
        System.out.println("CREATED PLUGIN");
        world.addWeapon(weaponName);
    }

    @Override
    public void onDisable(GameData data, World world) {

    }

}
