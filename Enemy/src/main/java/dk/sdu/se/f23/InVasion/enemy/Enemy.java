package dk.sdu.se.f23.InVasion.enemy;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.Point;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity {
    private List<Point> route = new ArrayList<>();
    private AIType type;
    private float speed;

    //Make new enemy with default AIType (STUPID)

    public Enemy() {
        this.type = AIType.STUPID;
        this.speed = 1000;
    }
    //Make new enemy with specific AIType

    public Enemy(AIType type) {
        this.type = type;
        this.speed = 1000;
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
}
