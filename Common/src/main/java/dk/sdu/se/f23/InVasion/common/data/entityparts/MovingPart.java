package dk.sdu.se.f23.InVasion.common.data.entityparts;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;

<<<<<<< HEAD:Common/src/main/java/dk/sdu/se/f23/InVasion/common/data/entityparts/MovingPart.java
=======
import data.Entity;
import data.GameData;
import data.Point;
import data.World;
>>>>>>> main:Common/src/main/java/data/entityparts/MovingPart.java

public class MovingPart implements EntityPart{
    private double speed;
    private Point pos;

    public MovingPart(double speed, Point pos) {
        this.speed = speed;
        this.pos = pos;
    }

    @Override
    public void process(GameData data, Entity entity) {

    }

    public double getSpeed() {
        return speed;
    }

    public Point getPos() {
        return pos;
    }

}
