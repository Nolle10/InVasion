package dk.sdu.se.f23.InVasion.common.events.events;

import dk.sdu.se.f23.InVasion.common.events.abstracts.SystemEvent;
import dk.sdu.se.f23.InVasion.common.events.enums.SystemSender;

public class WaveIsDoneEvent extends SystemEvent {

    public WaveIsDoneEvent(SystemSender source) {
        super(source);
    }
}
