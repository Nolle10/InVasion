package dk.sdu.se.f23.InVasion.common.events.events;

import dk.sdu.se.f23.InVasion.common.events.abstracts.SystemEvent;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.common.events.enums.SystemSender;

public class StateChangeEvent extends SystemEvent {
    private final GameStateEnum newState;
    public StateChangeEvent(GameStateEnum newState) {
        super(SystemSender.WaveManager);
        this.newState = newState;
    }

    public GameStateEnum getNewState() {
        return newState;
    }
}
