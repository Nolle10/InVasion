package dk.sdu.se.f23.InVasion.common.events.events;

import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.events.abstracts.SystemEvent;
import dk.sdu.se.f23.InVasion.common.events.enums.SystemSender;

public class BuyTowerEvent extends SystemEvent {
    private final Point position;

    private String name;
    public BuyTowerEvent(Point position, String name) {
        super(SystemSender.Shop);
        this.position = position;
        this.name = name;
    }

    public Point getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
