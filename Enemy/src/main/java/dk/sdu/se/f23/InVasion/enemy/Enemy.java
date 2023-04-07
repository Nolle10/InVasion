package dk.sdu.se.f23.InVasion.enemy;

import data.Entity;
import data.Point;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity {
    private List<Point> route = new ArrayList<>();
    private AIType type;

    //Make new enemy with default AIType (STUPID)
    public Enemy() {
        this.type = AIType.STUPID;
    }

    //Make new enemy with specific AIType
    public Enemy(AIType type) {
        this.type = type;
    }

    public List<Point> getRoute() {
        return route;
    }

    public void setRoute(List<Point> route) {
        this.route = route;
    }

    public AIType getAIType() {
        return type;
    }

}
