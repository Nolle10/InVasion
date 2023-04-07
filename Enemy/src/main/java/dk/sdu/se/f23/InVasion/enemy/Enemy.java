package dk.sdu.se.f23.InVasion.enemy;



import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.Point;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity {
    private List<Point> route = new ArrayList<>();

    public List<Point> getRoute() {
        return route;
    }
}
