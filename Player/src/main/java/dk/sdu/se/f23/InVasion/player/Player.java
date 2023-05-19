package dk.sdu.se.f23.InVasion.player;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;

public class Player extends Entity {
    private float lastShot;
    private GameStateEnum lastKnownGameState;

    public Player() {
        this.lastKnownGameState = GameStateEnum.MainScreen;
        this.lastShot = 0;
    }

    public boolean shouldShoot(float delta) {
        if (this.lastKnownGameState != GameStateEnum.PlayState) {
            return false;
        }
        this.lastShot += delta;
        if (this.lastShot >= 1) {
            this.lastShot = 0.0f;
            return true;
        }
        return false;
    }

    public void setLastKnownGameState(GameStateEnum lastKnownGameState) {
        this.lastKnownGameState = lastKnownGameState;
    }

    public GameStateEnum getLastKnownGameState() {
        return this.lastKnownGameState;
    }
}
