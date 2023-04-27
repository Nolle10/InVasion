package dk.sdu.se.f23.InVasion.player;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.services.PluginService;

public class PlayerPlugin implements PluginService {
    Entity player;

    @Override
    public void onEnable(GameData data, World world) {
        player = createPlayerInfo(data);
        world.addEntity(player);
    }

    private Entity createPlayerInfo(GameData data){
        Entity player = new Player();
        player.add(new PositionPart(new Point(data.getDisplayWidth() / 2, data.getDisplayHeight() / 2), 3.1415f / 2));
        return player;
    }

    @Override
    public void onDisable(GameData data, World world) {
        world.removeEntity(player);
    }
}
