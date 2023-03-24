package dk.sdu.se.f23.InVasion.events;

import dk.sdu.se.f23.InVasion.data.Entity;

import java.io.Serializable;

public class Event implements Serializable {
    private final Entity source;

    public Event(Entity source) {
        this.source = source;
    }

    public Entity getSource() {
        return source;
    }
}
