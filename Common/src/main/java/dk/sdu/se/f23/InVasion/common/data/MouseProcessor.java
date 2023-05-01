package dk.sdu.se.f23.InVasion.common.data;

import com.badlogic.gdx.InputAdapter;

public class MouseProcessor extends InputAdapter {
    private static MouseProcessor instance = new MouseProcessor();

    private MouseProcessor() {
    }

    public static MouseProcessor getInstance() {
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
