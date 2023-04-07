package dk.sdu.se.f23.InVasion.common.data.entityparts;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;

public class LifePart implements EntityPart {
    private int life;
    private boolean isHit = false;

    public LifePart(int life) {
        this.life = life;
    }

    @Override
    public void process(GameData data, Entity entity) {

    }

    public boolean isAlive() {
        if (isHit) {
            life = - 1;
            isHit = false;
        }
        return isAlive();
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
}
