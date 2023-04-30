package dk.sdu.se.f23.InVasion.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.events.BuyTowerEvent;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.TargetEvent;
import dk.sdu.se.f23.InVasion.common.services.PluginService;
import dk.sdu.se.f23.InVasion.commonweapon.Weapon;

import java.util.ArrayList;

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
        world.addEntity(createWeapon(new Point(1000, 0)));
        //Adding WeaponControlSystem as an EventListener for TargetEvent and BuyTowerEvent
        WeaponControlSystem weaponControlSystem = new WeaponControlSystem();
        EventDistributor.addListener(TargetEvent.class, weaponControlSystem);
        EventDistributor.addListener(BuyTowerEvent.class, weaponControlSystem);
    }

    private Entity createWeapon(Point position) {
        Entity weapon = new Weapon();
        weapon.add(new PositionPart(new Point(position.getX(), position.getY()), 0));
        weapon.setTexture(new Texture(Gdx.files.internal("Weapon/src/main/resources/TOWER.png")));
        return weapon;
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
