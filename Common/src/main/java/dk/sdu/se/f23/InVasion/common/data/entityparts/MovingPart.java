package dk.sdu.se.f23.InVasion.data.entityparts;

import dk.sdu.se.f23.InVasion.data.GameData;
import dk.sdu.se.f23.InVasion.data.Point;
import dk.sdu.se.f23.InVasion.data.World;

public class MovingPart implements EntityPart{
    private double speed;
    private Point pos;

    public MovingPart(double speed, Point pos) {
        this.speed = speed;
        this.pos = pos;
    }

    @Override
    public void process(GameData data, World world) {

    }

    public double getSpeed() {
        return speed;
    }

    public Point getPos() {
        return pos;
    }

}
