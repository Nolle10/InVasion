package dk.sdu.se.f23.InVasion.common.events;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.Point;

public class BuyTowerEvent extends Event {
    private Point position;

    public BuyTowerEvent(Entity source, Point position) {
        super(source);
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }
}
