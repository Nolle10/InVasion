package dk.sdu.se.f23.InVasion.common.events.events;

public class SpawnEnemysEvent extends Event {
    private int waveLevel;

    public SpawnEnemysEvent(int waveLevel) {
        this.waveLevel = waveLevel;
    }

    public int getWaveLevel() {
        return waveLevel;
    }
}
