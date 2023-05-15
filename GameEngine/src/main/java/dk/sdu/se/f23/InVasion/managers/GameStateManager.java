package dk.sdu.se.f23.InVasion.managers;


import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.common.events.events.SpawnEnemysEvent;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.gamestates.*;

import static dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum.WinState;


public class GameStateManager {

    private GameState gameState;
    private GameData gameData;
    private World world;

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
            gameData.setWaveCount(gameData.getWaveCount() + 1);
            if (gameData.getWaveCount() > 2){
                state = WinState;
            } else {
                EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.PlayState), world);
                gameState = new PlayState(this);
                EventDistributor.sendEvent(new SpawnEnemysEvent(gameData.getWaveCount()), world);
            }
        }
        if(state == GameStateEnum.ShopState) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.ShopState), world);
            gameState = new ShopState(this);
        }
        if(state == GameStateEnum.PauseState) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.PauseState), world);
            gameState = new PauseState(this);
        }
        if(state == WinState) {
            EventDistributor.sendEvent(new StateChangeEvent(WinState), world);
            gameState = new WinState(this);
        }
        if(state == GameStateEnum.LossState){
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.LossState), world);
            gameState = new LossState(this);
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