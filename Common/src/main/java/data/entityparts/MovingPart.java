package data.entityparts;

import data.Entity;
import data.GameData;
import data.Point;
import data.World;

public class MovingPart implements EntityPart{
    private double speed;
    private Point pos;
    private boolean up;

    public MovingPart(double speed, Point pos) {
        this.speed = speed;
        this.pos = pos;
    }

    @Override
    public void process(GameData data, Entity entity) {

    }

    public double getSpeed() {
        return speed;
    }

    public Point getPos() {
        return pos;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

}
