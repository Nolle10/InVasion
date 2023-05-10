package dk.sdu.se.f23.InVasion.common.events.events;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.events.abstracts.EntityEvent;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;

public class FireShotEvent extends EntityEvent {
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
