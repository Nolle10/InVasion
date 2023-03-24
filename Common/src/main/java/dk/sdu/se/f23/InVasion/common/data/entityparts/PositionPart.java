package dk.sdu.se.f23.InVasion.data.entityparts;

import dk.sdu.se.f23.InVasion.data.GameData;
import dk.sdu.se.f23.InVasion.data.Point;
import dk.sdu.se.f23.InVasion.data.World;

public class PositionPart implements EntityPart{
    private Point pos;

    public PositionPart(Point pos) {
        this.pos = pos;
    }

    @Override
    public void process(GameData data, World world) {

    }
}
