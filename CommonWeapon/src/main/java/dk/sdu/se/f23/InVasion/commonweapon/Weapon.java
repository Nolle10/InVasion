package dk.sdu.se.f23.InVasion.commonweapon;

import dk.sdu.se.f23.InVasion.common.data.Entity;

public class Weapon extends Entity {
    private float lastShot;

    public Weapon() {
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