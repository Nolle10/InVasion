package dk.sdu.se.f23.InVasion.vaccine;


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


public class VaccinePlugin implements PluginService {

    private final String weaponName = "Vaccine";
    private Texture texture = new Texture(Gdx.files.internal("Vaccine/src/main/resources/vac.png"));
    private int cost = 800;
    private Vaccine vaccine;


    @Override
    public void onEnable(GameData data, World world) {
        //Adding WeaponControlSystem as an EventListener for TargetEvent and BuyTowerEvent
        VaccineControlSystem vaccineControlSystem = new VaccineControlSystem();
        EventDistributor.addListener(TargetEvent.class, vaccineControlSystem);
        EventDistributor.addListener(BuyTowerEvent.class, vaccineControlSystem);

        vaccine = new Vaccine(weaponName, texture, cost);
        BuyableManager.addBuyable(vaccine);
    }


    @Override
    public void onDisable(GameData data, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Weapon.class) {
                world.removeEntity(e);
            }
        }
        BuyableManager.removeBuyable(vaccine);
    }
}
