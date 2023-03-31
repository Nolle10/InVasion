package dk.sdu.se.f23.InVasion.common.data.entityparts;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
public class PositionPart implements EntityPart{
    private Point pos;

    public PositionPart(Point pos) {
        this.pos = pos;
    }

    @Override
    public void process(GameData data, Entity entity) {

    }
}
