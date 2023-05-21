package dk.sdu.se.f23.InVasion.collision;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.commonbullet.Bullet;
import dk.sdu.se.f23.InVasion.commonenemy.Enemy;

public class CollisionDetector implements EntityProcessingService {

    @SuppressWarnings("unchecked")
    @Override
    public void process(GameData data, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            for (Entity bullet : world.getEntities(Bullet.class)) {
                if (!this.collides(enemy, bullet)) {
                    continue;
                }

                LifePart entityLife = enemy.getPart(LifePart.class);
                if (entityLife == null) {
                    continue;
                }

                entityLife.setHit(true);

                world.removeEntity(bullet);
            }
        }
    }

    public boolean collides(Entity entity, Entity entity2) {
        PositionPart entMov = entity.getPart(PositionPart.class);
        PositionPart entMov2 = entity2.getPart(PositionPart.class);

        int entityHeight = entity.getTexture().getHeight();
        int entityWidth = entity.getTexture().getWidth();
        int otherEntityHeight = entity2.getTexture().getHeight();
        int otherEntityWidth = entity2.getTexture().getWidth();

        return (entMov.getX() < (entMov2.getX() + otherEntityWidth) && (entMov.getX() + entityWidth) > entMov2.getX() &&
                entMov.getY() < (entMov2.getY() + otherEntityHeight) && (entMov.getY() + entityHeight) > entMov2.getY());
    }
}
