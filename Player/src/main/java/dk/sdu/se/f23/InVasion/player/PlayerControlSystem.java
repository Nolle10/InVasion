package dk.sdu.se.f23.InVasion.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;

public class PlayerControlSystem implements EntityProcessingService {
    private long lastShot = 0;


    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity player : world.getEntities(Player.class)) {
            lastShot += data.getDelta()*50;
            if (lastShot >= 0.5) {
                EventDistributor.sendEvent(new FireShotEvent(player,shotDirection()),world);
                lastShot = 0;
            }
            PositionPart part = player.getPart(PositionPart.class);
            data.getSpriteBatch().draw(player.getTexture(), part.getX(), part.getY());
        }
    }

    private Point shotDirection(){
        Gdx.input.setInputProcessor(MouseProcessor.getInstance());
        int mouseX = MouseProcessor.getInstance().getMousePositionX();
        int mouseY = Gdx.graphics.getHeight() - MouseProcessor.getInstance().getMousePositionY();
        return new Point(mouseX,mouseY);
    }
}
