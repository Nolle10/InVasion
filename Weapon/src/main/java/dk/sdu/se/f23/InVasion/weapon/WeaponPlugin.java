package dk.sdu.se.f23.InVasion.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.shop.BuyableManager;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.events.BuyTowerEvent;
import dk.sdu.se.f23.InVasion.common.events.events.TargetEvent;
import dk.sdu.se.f23.InVasion.common.services.PluginService;
import dk.sdu.se.f23.InVasion.commonweapon.Weapon;


public class WeaponPlugin implements PluginService {
    private final String weaponName = "Medicine";
    private Texture texture = new Texture(Gdx.files.internal("Weapon/src/main/resources/TOWER.png"));
    private int cost = 200;
    private Medicine medicine;


    @Override
    public void onEnable(GameData data, World world) {
        //Adding WeaponControlSystem as an EventListener for TargetEvent and BuyTowerEvent
        WeaponControlSystem weaponControlSystem = new WeaponControlSystem();

        EventDistributor.addListener(TargetEvent.class, weaponControlSystem);
        EventDistributor.addListener(BuyTowerEvent.class, weaponControlSystem);

        medicine = new Medicine(weaponName, texture, cost);

        BuyableManager.addBuyable(medicine);
    }

    @Override
    public void onDisable(GameData data, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Weapon.class) {
                world.removeEntity(e);
            }
        }
        BuyableManager.removeBuyable(medicine);
    }
/*
    public Texture getTexture() {
        return texture;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getCost() {
        return cost;
    }*/
}
