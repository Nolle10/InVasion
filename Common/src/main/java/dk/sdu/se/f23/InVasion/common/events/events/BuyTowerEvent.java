package dk.sdu.se.f23.InVasion.common.events.events;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.events.abstracts.SystemEvent;
import dk.sdu.se.f23.InVasion.common.events.enums.SystemSender;

public class BuyTowerEvent extends SystemEvent {
    private final Point position;

    public BuyTowerEvent(Point position) {
        super(SystemSender.Shop);
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

}
