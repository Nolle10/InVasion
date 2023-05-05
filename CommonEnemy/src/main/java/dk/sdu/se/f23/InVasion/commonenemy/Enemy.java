package dk.sdu.se.f23.InVasion.commonenemy;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Enemy extends Entity {
    private List<Point> route = new ArrayList<>();
    private float speed;
    private int routeStep;
    private float timeSinceLastMove;

    //Make new enemy with default AIType (STUPID)

    public Enemy(List<Point> route) {
        this.route = route;
        this.speed = 50;
        this.routeStep = 0;
        this.timeSinceLastMove = 0;
    }
    //Make new enemy with specific AIType


    public List<Point> getRoute() {
        return route;
    }

    public void setRoute(List<Point> route) {
        this.route = route;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public Point getNextPoint(float delta){
        this.timeSinceLastMove += delta*1000;
        if (this.timeSinceLastMove < this.speed){
            return route.get(this.routeStep);
        }

        this.timeSinceLastMove = 0;
        // TODO: Remove this when we are done with testing
        if (++this.routeStep % route.size() == 0){
            this.routeStep = 0;
            Collections.reverse(this.route);
        }
        return route.get(this.routeStep);
    }
}
