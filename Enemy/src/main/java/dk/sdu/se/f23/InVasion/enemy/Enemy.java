package dk.sdu.se.f23.InVasion.enemy;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Enemy extends Entity {
    private List<Point> route = new ArrayList<>();
    private AIType type;
    private float speed;
    private int routeStep;
    private float timeSinceLastMove;

    //Make new enemy with default AIType (STUPID)

    public Enemy(List<Point> route) {
        this.route = route;
        this.type = AIType.STUPID;
        this.speed = 50;
        this.routeStep = 0;
        this.timeSinceLastMove = 0;
    }
    //Make new enemy with specific AIType

    public Enemy(AIType type) {
        this.type = type;
        this.speed = 100;
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
