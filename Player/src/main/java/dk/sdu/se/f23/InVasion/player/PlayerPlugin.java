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
    Entity player;

    @Override
    public void onEnable(GameData data, World world) {
        player = createPlayer(data,world);
        world.addEntity(player);
        PlayerControlSystem playerControlSystem = new PlayerControlSystem();
        EventDistributor.addListener(StateChangeEvent.class, playerControlSystem);
    }

    private Entity createPlayer(GameData data, World world){
        Entity player = new Player();
        player.add(new PositionPart(new Point(200, 200), 3.1415f / 2));
        player.add(new LifePart(100));
        Texture texture = new Texture(Gdx.files.internal("Player/src/main/resources/images/player3.png"));
        player.setTexture(texture);

        return player;
    }

    @Override
    public void onDisable(GameData data, World world) {
        world.removeEntity(player);
    }
}
