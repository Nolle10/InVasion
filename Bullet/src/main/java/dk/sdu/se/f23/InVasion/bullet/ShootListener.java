package dk.sdu.se.f23.InVasion.bullet;

import com.badlogic.gdx.InputAdapter;

public class ShootListener extends InputAdapter {
    private static ShootListener instance = new ShootListener();

    private ShootListener() {
    }

    public static ShootListener getInstance() {
        return instance;
    }

    private int mousePositionX;
    private int mousePositionY;

    @Override
    public boolean mouseMoved(int xPos, int yPos) {
        setMousePositionX(xPos);
        setMousePositionY(yPos);
        return true;
    }

    //getters and setters
    public int getMousePositionX() {
        return mousePositionX;
    }

    public void setMousePositionX(int mousePositionX) {
        this.mousePositionX = mousePositionX;
    }

    public int getMousePositionY() {
        return mousePositionY;
    }

    public void setMousePositionY(int mousePositionY) {
        this.mousePositionY = mousePositionY;
    }
}
