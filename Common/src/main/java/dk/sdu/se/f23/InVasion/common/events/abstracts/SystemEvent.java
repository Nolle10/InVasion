package dk.sdu.se.f23.InVasion.common.events.abstracts;

import dk.sdu.se.f23.InVasion.common.events.enums.SystemSender;

public abstract class SystemEvent extends Event{
    private final SystemSender source;

    protected SystemEvent(SystemSender source) {
        this.source = source;
    }

    public SystemSender getSource() {
        return source;
    }
}
