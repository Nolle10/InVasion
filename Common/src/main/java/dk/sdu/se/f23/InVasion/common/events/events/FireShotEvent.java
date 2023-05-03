package dk.sdu.se.f23.InVasion.common.events.events;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.Point;

public class FireShotEvent extends Event {
    Point direction;

    public FireShotEvent(Entity source, Point direction) {
        super(source);
        this.direction = direction;
    }

    public Point getDirection() {
        return direction;
    }
    @Override
    public Entity getSource() {
        return super.getSource();
    }

}
