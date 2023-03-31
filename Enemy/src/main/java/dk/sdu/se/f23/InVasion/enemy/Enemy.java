package dk.sdu.se.f23.InVasion.enemy;

import data.Entity;
import data.Point;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity {
    private List<Point> route = new ArrayList<>();

    public List<Point> getRoute() {
        return route;
    }
}
