package dk.sdu.se.f23.InVasion.managers;


import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.common.events.events.SpawnEnemysEvent;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.gamestates.*;


public class GameStateManager {

    private GameState gameState;
    private GameData gameData;
    private World world;

    private int waveCount = 1;

    public GameStateManager(GameData data, World world) {
        this.world = world;
        this.gameData = data;
        setState(GameStateEnum.MainScreen);
    }

    public void setState(GameStateEnum state) {
        if(gameState != null) gameState.dispose();
        if(state == GameStateEnum.MainScreen) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.MainScreen), world);
            gameState = new MainScreenState(this);
        }
        if(state == GameStateEnum.PlayState) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.PlayState), world);
            gameState = new PlayState(this);
            EventDistributor.sendEvent(new SpawnEnemysEvent(waveCount), world);
            waveCount++;
        }
        if(state == GameStateEnum.ShopState) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.ShopState), world);
            gameState = new ShopState(this);
        }
        if(state == GameStateEnum.PauseState) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.PauseState), world);
            gameState = new PauseState(this);
        }
        if(state == GameStateEnum.WinState) {
            gameState = new EndState(this, 1);
        }
        if(state == GameStateEnum.LossState){
            gameState = new EndState(this, 0);
        }

        gameData.setCurrentState(state);
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

}