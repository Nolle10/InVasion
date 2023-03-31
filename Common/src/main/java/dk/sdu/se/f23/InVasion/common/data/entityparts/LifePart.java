package dk.sdu.se.f23.InVasion.common.data.entityparts;

<<<<<<< HEAD:Common/src/main/java/dk/sdu/se/f23/InVasion/common/data/entityparts/LifePart.java
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
=======
import data.Entity;
import data.GameData;
import data.World;
>>>>>>> main:Common/src/main/java/data/entityparts/LifePart.java

public class LifePart implements EntityPart {
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
