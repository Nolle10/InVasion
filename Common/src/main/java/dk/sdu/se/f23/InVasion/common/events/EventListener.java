package dk.sdu.se.f23.InVasion.common.events;

import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;

public interface EventListener {
    void processEvent(Event event, World world);
}
