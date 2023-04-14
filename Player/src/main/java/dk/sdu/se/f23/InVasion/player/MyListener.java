package dk.sdu.se.f23.InVasion.player;


import com.badlogic.gdx.InputProcessor;

public class MyListener implements InputProcessor {
    private static MyListener instance = new MyListener();
    private MyListener(){

    }
    public static MyListener getInstance(){
        return instance;
    }

    private int mousePositionX;
    private int mousePositionY;

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        System.out.println("moved to: "+i+" "+i1);
        setMousePositionX(i);
        setMousePositionY(i1);
        return true;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
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
