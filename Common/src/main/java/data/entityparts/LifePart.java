package data.entityparts;

import data.Entity;
import data.GameData;
import data.World;

public class LifePart implements EntityPart{
    private int life;
    private boolean isHit;
    @Override
    public void process(GameData data, Entity entity) {

    }

    public boolean isAlive() {
        return this.life < 0;
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
