package dk.sdu.se.f23.InVasion.managers;


import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.common.events.events.SpawnEnemysEvent;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
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

    public GameStateManager(GameData data, World world) {
        this.world = world;
        this.gameData = data;
        setState(MENU);
    }

    public void setState(int state) {
        if(gameState != null) gameState.dispose();
        if(state == MENU) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.MainScreen), world);
            gameState = new MainScreenState(this);
        }
        if(state == PLAY) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.PlayState), world);
            gameState = new PlayState(this);
            EventDistributor.sendEvent(new SpawnEnemysEvent(1), world);
        }
        if(state == SHOP) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.ShopState), world);
            gameState = new ShopState(this);
        }
        if(state == PAUSE) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.PauseState), world);
            gameState = new PauseState(this);
        }
        currentState = state;
    }

    public void update(float dt) {
        gameState.update(dt);
    }

    public void draw() {
        gameState.draw(gameData);
    }
    public World getWorld(){
        return world;
    }

    public GameData getGameData(){
        return  gameData;
    }
    public GameState getGameState() {
        return gameState; // i just put this here
    }
    public int getCurrentState(){
        return currentState;
    }
}