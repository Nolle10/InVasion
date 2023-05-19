package dk.sdu.se.f23.InVasion.vaccine;

import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.shop.Buyable;
import dk.sdu.se.f23.InVasion.commonweapon.Weapon;

public class Vaccine extends Weapon implements Buyable {
    private final String name;
    private final Texture pathToTexture;
    private final int price;
    private float lastShot = 0;
    public Vaccine(String name, Texture texture, int price) {
        this.name = name;
        this.pathToTexture = texture;
        this.price = price;
    }

    @Override
    public boolean shouldShoot(float delta) {
        this.lastShot += delta;
        if (this.lastShot >= 2) {
            this.lastShot = 0.0f;
            return true;
        }
        return false;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Texture getTexture() {
        return pathToTexture;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
