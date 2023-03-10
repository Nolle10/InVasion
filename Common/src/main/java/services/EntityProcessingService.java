package services;

import data.GameData;
import data.World;

public interface EntityProcessingService {
    void process(GameData data, World world);
}
