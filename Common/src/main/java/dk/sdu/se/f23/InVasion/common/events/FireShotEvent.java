package dk.sdu.se.f23.InVasion.common.events;

import dk.sdu.se.f23.InVasion.common.data.Entity;

public class FireShotEvent extends Event{

    private double direction;
    public FireShotEvent(Entity source, double direction) {
        super(source);
        this.direction = direction;
    }

    @Override
    public Entity getSource() {
        return super.getSource();
    }

    public double getDirection() {
        return this.direction;
    }
}
