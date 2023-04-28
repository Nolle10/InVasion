package dk.sdu.se.f23.InVasion.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.common.services.PluginService;
import dk.sdu.se.f23.InVasion.commonweapon.Weapon;

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



    @Override
    public void onDisable(GameData data, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Weapon.class) {
                world.removeEntity(e);
            }
        }

    }

}
