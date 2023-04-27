package dk.sdu.se.f23.InVasion.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.ProcessAt;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;

public class PlayerControlSystem implements EntityProcessingService {
    private long lastShot = 0;

    @Override
    public void process(GameData data, World world) {

    }

    private void createPlayer(Entity player) {
        PositionPart positionPart = player.getPart(PositionPart.class);
        SpriteBatch spriteBatch = new SpriteBatch();
        Texture texture = new Texture(Gdx.files.internal("Player/src/main/resources/images/tower.png"));

        player.setTexture(texture);

        spriteBatch.begin();
        spriteBatch.draw(texture, positionPart.getX(), positionPart.getY());
        spriteBatch.end();
    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity player : world.getEntities(Player.class)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastShot >= 500) {
                EventDistributor.sendEvent(new FireShotEvent(player),world);
                lastShot = currentTime;
            }
            createPlayer(player);
        }
    }
}
