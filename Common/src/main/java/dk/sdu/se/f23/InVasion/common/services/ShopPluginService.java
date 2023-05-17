package dk.sdu.se.f23.InVasion.common.services;

import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;


public interface ShopPluginService {

    void onEnable(GameData data, World world);

    void onDisable(GameData data, World world);

    Texture getTexture();

    String getWeaponName();

    int getCost();
}
