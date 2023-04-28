package dk.sdu.se.f23.InVasion.common.services;

import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.ProcessAt;
import dk.sdu.se.f23.InVasion.common.data.World;

public interface EntityProcessingService {
    void process(GameData data, World world, ProcessAt processTime);
}
