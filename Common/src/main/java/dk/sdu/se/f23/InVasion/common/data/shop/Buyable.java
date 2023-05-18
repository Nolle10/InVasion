package dk.sdu.se.f23.InVasion.common.data.shop;

import com.badlogic.gdx.graphics.Texture;

public interface Buyable {
    String getName();
    Texture getTexture();
    int getPrice();
}
