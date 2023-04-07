package managers;


import gamestates.*;
import data.GameData;

public class GameStateManager {

    // current game state
    private GameState gameState;

    private GameData gameData;
    public static final int MENU = 0;
    public static final int SHOP = 1;
    public static final int PLAY = 2;
    public static final int PAUSE = 3;


    public GameStateManager() {
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
    }

    public void update(float dt) {
        gameState.update(dt);
    }

    public void draw() {
        gameState.draw();
    }

}