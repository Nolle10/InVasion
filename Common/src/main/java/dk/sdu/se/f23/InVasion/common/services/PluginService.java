package dk.sdu.se.f23.InVasion.services;

import dk.sdu.se.f23.InVasion.data.GameData;
import dk.sdu.se.f23.InVasion.data.World;

public interface PluginService {
    void onEnable(GameData data, World world);

    void onDisable(GameData data, World world);
}
