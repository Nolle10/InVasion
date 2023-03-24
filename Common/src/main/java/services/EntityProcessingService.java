package services;

import data.GameData;
import data.ProcessAt;
import data.World;

public interface EntityProcessingService {
    @Deprecated
    void process(GameData data, World world);
    void process(GameData data, World world, ProcessAt processTime);
}
