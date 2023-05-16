package dk.sdu.se.f23.InVasion.common.events.events;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.events.abstracts.SystemEvent;
import dk.sdu.se.f23.InVasion.common.events.enums.SystemSender;

public class BuyTowerEvent extends SystemEvent {
    private final Point position;
    private final Entity sender;

    public BuyTowerEvent(Point position, Entity sender) {
        super(SystemSender.Shop);
        this.position = position;
        this.sender = sender;
    }

    public Point getPosition() {
        return position;
    }

    public Entity getSender() {
        return sender;
    }
}
