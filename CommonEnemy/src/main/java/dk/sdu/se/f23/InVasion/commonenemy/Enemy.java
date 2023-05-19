package dk.sdu.se.f23.InVasion.commonenemy;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;

import java.util.Collections;
import java.util.List;

public class Enemy extends Entity {
    private final List<Point> route;
    private final float SPEED;
    private int routeStep;
    private float timeSinceLastMove;
    private int damage;

    public Enemy(List<Point> route) {
        this.route = route;
        this.SPEED = 50;
        this.routeStep = 0;
        this.timeSinceLastMove = 0;
        this.damage = 1;
    }

    public Point getNextPoint(float delta, World world, Entity enemy) {
        this.timeSinceLastMove += delta * 1000;
        if (this.timeSinceLastMove < this.SPEED) {
            return route.get(this.routeStep);
        }
        this.timeSinceLastMove = 0;

        //Check if current point is goal state
        if (world.getGoalState().equals(route.get(this.routeStep))) {
            world.setBaseHealth(world.getBaseHealth()-damage);
            world.removeEntity(enemy);
        }
        this.routeStep++;
        return route.get(this.routeStep);
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
