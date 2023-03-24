package managers;


import gamestates.GameState;
import gamestates.PlayState;
import data.GameData;

public class GameStateManager {

    // current game state
    private GameState gameState;

    private GameData gameData;
    public static final int MENU = 0;
    public static final int PLAY = 893746;

    public GameStateManager() {
        setState(PLAY);
    }

    public void setState(int state) {
        if(gameState != null) gameState.dispose();
        if(state == MENU) {
            // gameState = new MenuState(this);
        }
        if(state == PLAY) {
            gameState = new PlayState(this);
        }
    }

    public void update(float dt) {
        gameState.update(dt);
    }

    public void draw() {
        gameState.draw();
    }

}