package dk.sdu.se.f23.InVasion.common.events;

import dk.sdu.se.f23.InVasion.common.data.Entity;

public class FireShotEvent extends Event{

    public FireShotEvent(Entity source) {
        super(source);
    }

    @Override
    public Entity getSource() {
        return super.getSource();
    }

}
