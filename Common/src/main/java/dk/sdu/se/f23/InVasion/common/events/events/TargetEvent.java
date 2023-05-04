package dk.sdu.se.f23.InVasion.common.events.events;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.Point;

public class TargetEvent extends Event{
    Point target;

    public TargetEvent(Entity source, Point target) {
        super(source);
        this.target = target;
    }

    public Point getTarget() {
        return target;
    }
}