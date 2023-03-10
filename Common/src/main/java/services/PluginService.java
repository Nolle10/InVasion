package services;

import data.Data;
import data.World;

public interface PluginService {
    void onEnable(Data data, World world);

    void onDisable(Data data, World world);
}
