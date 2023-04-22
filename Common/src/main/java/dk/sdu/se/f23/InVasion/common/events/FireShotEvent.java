package dk.sdu.se.f23.InVasion.common.events;

import dk.sdu.se.f23.InVasion.common.data.Entity;

public class FireShotEvent extends Event{

    private String shooterType;
    public FireShotEvent(Entity source) {
        super(source);
        this.shooterType = getShooterType();
    }

    @Override
    public Entity getSource() {
        return super.getSource();
    }

    public String getShooterType() {
        return this.getSource().getClass().getName();
    }
}
