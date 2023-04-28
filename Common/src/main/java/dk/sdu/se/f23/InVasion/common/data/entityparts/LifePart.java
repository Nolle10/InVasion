package dk.sdu.se.f23.InVasion.common.data.entityparts;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;

public class LifePart implements EntityPart {
    private int life;
    private boolean isHit = false;
    private boolean isDead = false;

    public LifePart(int life) {
        this.life = life;
    }

    @Override
    public void process(GameData data, Entity entity) {
        if (isHit) {
            life -= 1;
            isHit = false;
        }
        if (life <= 0) {
            isDead = true;
        }
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isNotDead() {
        if (isDead == isDead()){
            return false;
        }
        else{
            return true;
        }
    }
}
