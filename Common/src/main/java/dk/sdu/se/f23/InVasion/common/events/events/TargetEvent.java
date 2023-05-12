package dk.sdu.se.f23.InVasion.common.events.events;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.events.abstracts.EntityEvent;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;

public class TargetEvent extends EntityEvent {
    Point target;

    public TargetEvent(Entity source, Point target) {
        super(source);
        this.target = target;
    }

    public Point getTarget() {
        return target;
    }
}
