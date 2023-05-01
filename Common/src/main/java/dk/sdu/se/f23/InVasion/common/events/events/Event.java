package dk.sdu.se.f23.InVasion.common.events.events;

import dk.sdu.se.f23.InVasion.common.data.Entity;

import java.io.Serializable;

public class Event implements Serializable {
    private final Entity source;

    public Event(){
        this.source = null;
    }
    public Event(Entity source) {
        this.source = source;
    }

    public Entity getSource() {
        return source;
    }
}
