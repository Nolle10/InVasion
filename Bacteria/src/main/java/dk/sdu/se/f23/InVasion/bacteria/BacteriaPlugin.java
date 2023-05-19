package dk.sdu.se.f23.InVasion.bacteria;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.events.SpawnEnemysEvent;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.common.services.PluginService;

public class BacteriaPlugin implements PluginService {
    Entity enemy;
    BacteriaControlSystem bacteriaControlSystem;


    @Override
    public void onEnable(GameData data, World world) {

        this.bacteriaControlSystem = new BacteriaControlSystem();
        EventDistributor.addListener(SpawnEnemysEvent.class, bacteriaControlSystem);
    }

    @Override
    public void onDisable(GameData data, World world) {
        EventDistributor.removeListener(StateChangeEvent.class, bacteriaControlSystem);
        EventDistributor.removeListener(SpawnEnemysEvent.class, bacteriaControlSystem);
        world.removeEntity(enemy);
    }
}
