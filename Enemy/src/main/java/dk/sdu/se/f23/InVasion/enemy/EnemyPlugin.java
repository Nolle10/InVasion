package dk.sdu.se.f23.InVasion.enemy;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.Entity;

import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MoneyPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MovingPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.events.events.SpawnEnemysEvent;
import dk.sdu.se.f23.InVasion.common.services.PluginService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        EventDistributor.removeListener(SpawnEnemysEvent.class, enemyControlSystem);
        world.removeEntity(enemy);
    }
}
