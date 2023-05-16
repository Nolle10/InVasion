package dk.sdu.se.f23.InVasion.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;
import dk.sdu.se.f23.InVasion.common.events.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



class PlayerControlSystemTest {
    private GameData gameData;
    private World world;
    private SpriteBatch spriteBatch;
    private Texture playerTexture;
    private PlayerControlSystem playerControlSystem;

    @BeforeEach
    void setUp() {
        //Mockito
        /*gameData = mock(GameData.class);
        world = mock(World.class);
        spriteBatch = mock(SpriteBatch.class);
        playerTexture = mock(Texture.class);*/
        gameData = new GameData();
        world = new World();
        spriteBatch = new SpriteBatch();
        playerTexture = new Texture("player.png");

        playerControlSystem = new PlayerControlSystem();
    }

    @Test
    void process() {
        // Arrange
        Entity player = new Player();
        player.setTexture(playerTexture);
        world.addEntity(player);
        when(gameData.getDelta()).thenReturn(1f);
        when(MouseProcessor.getInstance().getMousePositionX()).thenReturn(10);
        when(MouseProcessor.getInstance().getMousePositionY()).thenReturn(20);

        // Act
        playerControlSystem.process(gameData, world, ProcessAt.Tick);

        // Assert
        verify(gameData, times(1)).getDelta();
        verify(MouseProcessor.getInstance(), times(1)).getMousePositionX();
        verify(MouseProcessor.getInstance(), times(1)).getMousePositionY();
        verify(spriteBatch, times(1)).draw(playerTexture, 0, 0);
        EventDistributor.sendEvent(any(FireShotEvent.class), eq(world));

    }

    @Test
    void processEvent() {
    }
}