package dk.sdu.se.f23.InVasion.player;

import com.badlogic.gdx.Gdx;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.EventListener;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.common.events.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;

public class PlayerControlSystem implements EntityProcessingService, EventListener {

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity player : world.getEntities(Player.class)) {
            if (((Player) player).getLastKnownGameState() == GameStateEnum.PlayState
                    || ((Player) player).getLastKnownGameState() == GameStateEnum.ShopState) {
                if (((Player) player).shouldShoot(data.getDelta())) {
                    EventDistributor.sendEvent(new FireShotEvent(player, shotDirection(data)), world);
                }

                PositionPart part = player.getPart(PositionPart.class);
                data.getSpriteBatch().draw(player.getTexture(), part.getX(), part.getY(), Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 15);
            }
        }
    }

    private Point shotDirection(GameData data) {
        int mouseX = MouseProcessor.getInstance().getMousePositionX();
        int mouseY = Gdx.graphics.getHeight() - MouseProcessor.getInstance().getMousePositionY();
        return new Point(mouseX, mouseY);
    }

    @Override
    public void processEvent(Event event, World world) {
        if (event instanceof StateChangeEvent) {
            for (Entity player : world.getEntities(Player.class)) {
                ((Player) player).setLastKnownGameState(((StateChangeEvent) event).getNewState());
            }
        }
    }
}
