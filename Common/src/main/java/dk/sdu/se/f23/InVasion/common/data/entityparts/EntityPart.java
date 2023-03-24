package dk.sdu.se.f23.InVasion.data.entityparts;

import dk.sdu.se.f23.InVasion.data.GameData;
import dk.sdu.se.f23.InVasion.data.World;

public interface EntityPart {
    void process(GameData data, World world);
}
