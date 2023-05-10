package dk.sdu.se.f23.InVasion.common.events.abstracts;

import dk.sdu.se.f23.InVasion.common.data.Entity;

public abstract class EntityEvent extends Event{
    private final Entity source;

    public EntityEvent(Entity entity) {
        source = entity;
    }

    public Entity getSource() {
        return source;
    }
}
