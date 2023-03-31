package playerpackage;

import data.GameData;
import data.World;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.PluginService;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerPluginTest {
    GameData gameData;
    World worldActual, worldExpected;
    PlayerPlugin playerPlugin;

    @Before
    public void createValues(){
        gameData = new GameData();
        worldActual = new World();
        worldExpected = worldActual;
        playerPlugin = new PlayerPlugin();

    }
    @Test
    public void onDisable() {
        playerPlugin.onEnable(gameData, worldActual);
        System.out.printf("The ID of the player is the following: %s", playerPlugin.player.getID());
        playerPlugin.onDisable(gameData, worldActual);

        Assert.assertEquals(worldExpected, worldActual);
    }
}