package dk.sdu.se.f23.InVasion.managers;


import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.gamestates.*;


public class GameStateManager {

    // current game state
    private GameState gameState;

    private GameData gameData;
    public static final int MENU = 0;
    public static final int SHOP = 1;
    public static final int PLAY = 2;
    public static final int PAUSE = 3;
    private World world;
    private int currentState = 0;

    public GameStateManager(World world) {
        this.world = world;
        setState(MENU);
    }

    public void setState(int state) {
        if(gameState != null) gameState.dispose();
        if(state == MENU) {
            gameState = new MainScreenState(this);
        }
        if(state == PLAY) {
            gameState = new PlayState(this);
        }
        if(state == SHOP) {
            gameState = new ShopState(this);
        }
        if(state == PAUSE) {
            gameState = new PauseState(this);
        }
        currentState = state;
    }

    public void update(float dt) {
        gameState.update(dt);
    }

    public void draw() {
        gameState.draw();
    }
    public World getWorld(){
        return world;
    }
    public GameState getGameState() {
        return gameState; // i just put this here
    }
    public int getCurrentState(){
        return currentState;
    }
}