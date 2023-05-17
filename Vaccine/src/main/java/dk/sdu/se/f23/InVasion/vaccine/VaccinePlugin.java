package dk.sdu.se.f23.InVasion.vaccine;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.events.events.BuyTowerEvent;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.common.events.events.TargetEvent;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.services.PluginService;
import dk.sdu.se.f23.InVasion.common.services.ShopPluginService;
import dk.sdu.se.f23.InVasion.commonweapon.Weapon;

import java.util.ArrayList;


public class VaccinePlugin implements ShopPluginService {

    private final String weaponName = "Vaccine";
    private Texture texture = new Texture(Gdx.files.internal("Vaccine/src/main/resources/vac.png"));
    private int cost = 800;


    @Override
    public void onEnable(GameData data, World world) {
        //Adding WeaponControlSystem as an EventListener for TargetEvent and BuyTowerEvent
        VaccineControlSystem vaccineControlSystem = new VaccineControlSystem();
        EventDistributor.addListener(TargetEvent.class, vaccineControlSystem);
        EventDistributor.addListener(BuyTowerEvent.class, vaccineControlSystem);
    }


    @Override
    public void onDisable(GameData data, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Weapon.class) {
                world.removeEntity(e);
            }
        }

    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public String getWeaponName() {
        return weaponName;
    }

    @Override
    public int getCost() {
        return cost;
    }

}
