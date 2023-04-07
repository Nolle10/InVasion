package dk.sdu.se.f23.InVasion.managers;


import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.gamestates.GameState;
import dk.sdu.se.f23.InVasion.gamestates.PlayState;


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