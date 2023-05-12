package dk.sdu.se.f23.InVasion.common.events.events;

import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;
import dk.sdu.se.f23.InVasion.common.events.abstracts.SystemEvent;
import dk.sdu.se.f23.InVasion.common.events.enums.SystemSender;

public class SpawnEnemysEvent extends SystemEvent {
    private int waveLevel;

    public SpawnEnemysEvent(int waveLevel) {
        super(SystemSender.StateManager);
        this.waveLevel = waveLevel;
    }

    public int getWaveLevel() {
        return waveLevel;
    }
}
