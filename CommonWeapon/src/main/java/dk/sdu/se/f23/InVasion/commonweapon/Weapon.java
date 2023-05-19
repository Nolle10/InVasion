package dk.sdu.se.f23.InVasion.commonweapon;

import dk.sdu.se.f23.InVasion.common.data.Entity;

public class Weapon extends Entity {
    private float lastShot;
    private float shootingSpeed = 0.5f;
    public Weapon() {
        this.lastShot = 0;
    }

    public boolean shouldShoot(float delta) {
        this.lastShot += delta;
        if (this.lastShot >= shootingSpeed) {
            this.lastShot = 0.0f;
            return true;
        }
            return false;
    }

    public float getShootingSpeed(){
        return shootingSpeed;
    }
    public void setShootingSpeed(float shootingSpeed){
        this.shootingSpeed = shootingSpeed;
    }
}