package dk.sdu.se.f23.InVasion.managers;


import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.EventListener;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.common.events.events.SpawnEnemysEvent;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.common.events.events.WaveIsDoneEvent;
import dk.sdu.se.f23.InVasion.commonenemy.Enemy;
import dk.sdu.se.f23.InVasion.gamestates.*;


public class GameStateManager implements EventListener {

    private GameState gameState;
    private GameData gameData;
    private World world;
    private boolean shouldStartWave = false;

    public GameStateManager(GameData data, World world) {
        EventDistributor.addListener(WaveIsDoneEvent.class, this);
        this.world = world;
        this.gameData = data;
        setState(GameStateEnum.MainScreen);
    }

    public void setState(GameStateEnum state) {
        if (gameState != null) gameState.dispose();
        if (state == GameStateEnum.MainScreen) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.MainScreen), world);
            gameState = new MainScreenState(this);
        }
        if (state == GameStateEnum.PlayState) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.PlayState), world);
            if (shouldStartWave){
                gameData.setWaveCount(gameData.getWaveCount() + 1);
                EventDistributor.sendEvent(new SpawnEnemysEvent(gameData.getWaveCount()), world);
            }
            gameState = new PlayState(this);
        }

        if (state == GameStateEnum.ShopState) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.ShopState), world);
            gameState = new ShopState(this);
            shouldStartWave = true;

        }
        if (state == GameStateEnum.PauseState) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.PauseState), world);
            gameState = new PauseState(this);
            shouldStartWave = false;
        }
        if (state == GameStateEnum.WinState) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.WinState), world);
            gameState = new WinState(this);
            gameData.setWaveCount(0);
        }
        if (state == GameStateEnum.LossState) {
            EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.LossState), world);
            gameState = new LossState(this);
            gameData.setWaveCount(0);
        }

        gameData.setCurrentState(state);
    }

    public void update(float dt) {
        gameState.update(dt);
    }

    public void draw() {
        gameState.draw(gameData);
    }

    public World getWorld() {
        return world;
    }

    public GameData getGameData() {
        return gameData;
    }

    @Override
    public void processEvent(Event event, World world) {
        if (event instanceof WaveIsDoneEvent) {
            if (gameData.getWaveCount() > 4) {
                setState(GameStateEnum.WinState);
            } else {
                setState(GameStateEnum.ShopState);
            }
        }
    }
}