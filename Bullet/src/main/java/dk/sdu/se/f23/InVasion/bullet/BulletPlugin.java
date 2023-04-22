package dk.sdu.se.f23.InVasion.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MovingPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.TimerPart;
import dk.sdu.se.f23.InVasion.common.events.Event;
import dk.sdu.se.f23.InVasion.common.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.services.PluginService;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class BulletPlugin implements PluginService{
    Entity bullet;
    @Override
    public void onEnable(GameData data, World world) {
        List<Entity> shooterEntities = new ArrayList<>();
        for (Event e : data.getEvents()) {
            if (e.getClass() == FireShotEvent.class) {
                shooterEntities.add(e.getSource());
            }
        }
        for (Entity e : shooterEntities){
            world.addEntity(createBullet(data, e));
        }

    }

    @Override
    public void onDisable(GameData data, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }
    public Entity createBullet(GameData gameData, Entity shooter) {
        PositionPart shooterPos = shooter.getPart(PositionPart.class);
        float x = shooterPos.getX();
        float y = shooterPos.getY();
        float radians = shooterPos.getRadians();
        float dt = gameData.getDelta();
        float speed = 350;

        Entity bullet = new Bullet();

        float bx = (float) cos(radians);
        float by = (float) sin(radians);
        bullet.setTexture(new Texture(Gdx.files.internal("Bullet/src/main/resources/star2.png")));

        bullet.add(new PositionPart(new Point((int) (bx + x), (int) (by + y)), radians));
        bullet.add(new LifePart(1));
        bullet.add(new MovingPart(0, 5000000, speed, 5));
        bullet.add(new TimerPart(2));

        return bullet;
    }
}
