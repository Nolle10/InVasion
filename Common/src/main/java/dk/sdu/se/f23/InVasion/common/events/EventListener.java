package dk.sdu.se.f23.InVasion.common.events;

import dk.sdu.se.f23.InVasion.common.data.World;

public interface EventListener {
    void processEvent(Event event, World world);
}
