package dk.sdu.se.f23.InVasion.common.data.entityparts;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;

public class LifePart implements EntityPart {
    private int life;
    private boolean isHit = false;
    private boolean isDead = false;

    public LifePart(int life) {
        setLife(life);
    }

    @Override
    public void process(GameData data, Entity entity) {
        if (isHit()) {
            setLife(getLife() - 1);
            setHit(false);
        }
        if (getLife() <= 0) {
            isDead = true;
        }
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.isDead = life < 0;
        this.life = life;
    }

    public void takeDamage(int damage) {
        setLife(getLife() - damage);
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
}
