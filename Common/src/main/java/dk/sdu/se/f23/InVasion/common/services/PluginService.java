package dk.sdu.se.f23.InVasion.common.services;

import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;

public interface PluginService {
    void onEnable(GameData data, World world);

    void onDisable(GameData data, World world);
}
