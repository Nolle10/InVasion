package dk.sdu.se.f23.InVasion.player;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.services.PluginService;

import java.util.Random;

public class PlayerPlugin implements PluginService {
    Entity player;

    @Override
    public void onEnable(GameData data, World world) {
        player = createPlayerInfo(data);
        world.addEntity(player);
    }

    private Entity createPlayerInfo(GameData data){
        Entity player = Player.getInstance();
        float x = data.getDisplayWidth()/2*5;
        float y = data.getDisplayHeight()/2*30;
        float radians = 3.1415f / 2;
        player.add(new PositionPart(new Point((int) x, (int) y), radians));
        return player;
    }

    @Override
    public void onDisable(GameData data, World world) {
        world.removeEntity(player);
    }
}
