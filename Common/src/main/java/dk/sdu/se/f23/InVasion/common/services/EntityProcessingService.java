package dk.sdu.se.f23.InVasion.services;

import dk.sdu.se.f23.InVasion.data.GameData;
import dk.sdu.se.f23.InVasion.data.ProcessAt;
import dk.sdu.se.f23.InVasion.data.World;

public interface EntityProcessingService {
    @Deprecated
    void process(GameData data, World world);
    void process(GameData data, World world, ProcessAt processTime);
}
