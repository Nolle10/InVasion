package dk.sdu.se.f23.InVasion.commonenemy;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.Point;

import java.util.Collections;
import java.util.List;

public class Enemy extends Entity {
    private final List<Point> route;
    private final float SPEED;
    private int routeStep;
    private float timeSinceLastMove;

    public Enemy(List<Point> route) {
        this.route = route;
        this.speed = 50;
        this.routeStep = 0;
        this.timeSinceLastMove = 0;
    }

    public Point getNextPoint(float delta) {
        this.timeSinceLastMove += delta * 1000;
        if (this.timeSinceLastMove < this.speed) {
            return route.get(this.routeStep);
        }

        this.timeSinceLastMove = 0;
        // TODO: Remove this when we are done with testing
        if (++this.routeStep % route.size() == 0) {
            this.routeStep = 0;
            Collections.reverse(this.route);
        }
        return route.get(this.routeStep);
    }
}
