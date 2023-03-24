package data.entityparts;

import data.Entity;
import data.GameData;
import data.Point;
import data.World;

public class PositionPart implements EntityPart{
    private Point pos;

    public PositionPart(Point pos) {
        this.pos = pos;
    }

    @Override
    public void process(GameData data, Entity entity) {

    }
}
