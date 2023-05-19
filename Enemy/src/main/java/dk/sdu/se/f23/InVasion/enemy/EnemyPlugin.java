package dk.sdu.se.f23.InVasion.enemy;


import dk.sdu.se.f23.InVasion.common.data.Entity;

import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.events.SpawnEnemysEvent;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.common.services.PluginService;


public class EnemyPlugin implements PluginService {
    Entity enemy;
    EnemyControlSystem enemyControlSystem;

    @Override
    public void onEnable(GameData data, World world) {
        this.enemyControlSystem = new EnemyControlSystem();
        EventDistributor.addListener(SpawnEnemysEvent.class, enemyControlSystem);
    }

    @Override
    public void onDisable(GameData data, World world) {
        EventDistributor.removeListener(StateChangeEvent.class, enemyControlSystem);
        EventDistributor.removeListener(SpawnEnemysEvent.class, enemyControlSystem);
        world.removeEntity(enemy);
    }
}
