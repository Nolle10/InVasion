package dk.sdu.se.f23.InVasion.player;

import dk.sdu.se.f23.InVasion.common.data.Entity;

public class Player extends Entity {
    private float lastShot;

    public Player() {
        this.lastShot = 0;
    }

    public boolean shouldShoot(float delta) {
        this.lastShot += delta;
        if (this.lastShot >= 1) {
            this.lastShot = 0.0f;
            return true;
        }
        return false;
    }
}
