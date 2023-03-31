package dk.sdu.se.f23.InVasion.common.data.entityparts;

<<<<<<< HEAD:Common/src/main/java/dk/sdu/se/f23/InVasion/common/data/entityparts/PositionPart.java
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
=======
import data.Entity;
import data.GameData;
import data.Point;
import data.World;
>>>>>>> main:Common/src/main/java/data/entityparts/PositionPart.java

public class PositionPart implements EntityPart{
    private Point pos;

    public PositionPart(Point pos) {
        this.pos = pos;
    }

    @Override
    public void process(GameData data, Entity entity) {

    }
}
