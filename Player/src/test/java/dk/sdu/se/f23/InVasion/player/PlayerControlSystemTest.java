package dk.sdu.se.f23.InVasion.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;



class PlayerControlSystemTest {
    private GameData gameData;
    private World world;
    private SpriteBatch spriteBatch;
    private Texture playerTexture;
    private PlayerControlSystem playerControlSystem;

    @BeforeEach
    void setUp() {
        gameData = mock(GameData.class);
        world = mock(World.class);
        spriteBatch = mock(SpriteBatch.class);
        playerTexture = mock(Texture.class);
        playerControlSystem = new PlayerControlSystem();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void process() {
    }

    @Test
    void processEvent() {
    }
}