package dk.sdu.se.f23.InVasion.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.ProcessAt;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.common.services.PluginService;

import java.util.ArrayList;
import java.util.Objects;

public class WeaponPlugin implements PluginService {
    private String weaponName = "Steven";
    private Texture texture = new Texture(Gdx.files.internal("Weapon/src/main/resources/TOWER.png"));
    private int cost = 200;


    @Override
    public void onEnable(GameData data, World world) {
        ArrayList<Object> weaponVariables = new ArrayList<>();
        weaponVariables.add(weaponName);
        weaponVariables.add(texture);
        weaponVariables.add(cost);
        world.addWeapon(weaponVariables);

    }


    public void createWeapon(){
    }
    @Override
    public void onDisable(GameData data, World world) {

    }

}
