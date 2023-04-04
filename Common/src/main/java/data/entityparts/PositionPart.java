package data.entityparts;

import data.Entity;
import data.GameData;
import data.Point;
import data.World;

public class PositionPart implements EntityPart{
    private Point pos;
    private float x;
    private float y;
    private float radians;

    public PositionPart(Point pos, float radians) {
        this.pos = pos;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadians() {
        return radians;
    }

    public void setX(float newX) {
        this.x = newX;
    }

    public void setY(float newY) {
        this.y = newY;
    }

    public void setPosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setRadians(float radians) {
        this.radians = radians;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
    }

    public Point getPos() {
        return new Point((int) x, (int) y);
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

}
