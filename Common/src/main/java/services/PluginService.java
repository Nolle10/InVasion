package services;

import data.GameData;
import data.World;

public interface PluginService {
    void onEnable(GameData data, World world);

    void onDisable(GameData data, World world);
}
