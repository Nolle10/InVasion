package dk.sdu.se.f23.InVasion.common.data.entityparts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;



import static java.lang.Math.*;


public class MovingPart implements EntityPart{
    private float dx, dy;
    private float acceleration;
    private float speed;
    private boolean up;
    private Point direction;

    public MovingPart(Point direction, float acceleration, float speed) {
        this.direction = direction;
        this.acceleration = acceleration;
        this.speed = speed;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();

        float angle = MathUtils.atan2(direction.getY() - y, direction.getX() - x) * MathUtils.radiansToDegrees;
        positionPart.setRadians(angle - 90);
        dx = MathUtils.cosDeg(angle) * speed;
        dy = MathUtils.sinDeg(angle) * speed;
        float radians = positionPart.getRadians();
        float dt = gameData.getDelta();

        // accelerating
        if (up) {
            dx += cos(radians) * acceleration * dt;
            dy += sin(radians) * acceleration * dt;
        }
        positionPart.setX(x + dx * Gdx.graphics.getDeltaTime());
        positionPart.setY(y + dy * Gdx.graphics.getDeltaTime());
    }
}
