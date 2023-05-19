package dk.sdu.se.f23.InVasion.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.common.services.PluginService;

public class PlayerPlugin implements PluginService {
    PlayerControlSystem playerControlSystem;

    @Override
    public void onEnable(GameData data, World world) {
        playerControlSystem = new PlayerControlSystem();
        EventDistributor.addListener(StateChangeEvent.class, playerControlSystem);
    }



    @Override
    public void onDisable(GameData data, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Player.class) {
                world.removeEntity(e);
            }
        }
        EventDistributor.removeListener(StateChangeEvent.class, playerControlSystem);
    }
}
