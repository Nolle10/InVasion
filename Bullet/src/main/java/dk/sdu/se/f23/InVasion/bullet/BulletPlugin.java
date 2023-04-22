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
    @Override
    public void onEnable(GameData data, World world) {
    }

    @Override
    public void onDisable(GameData data, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }
}
