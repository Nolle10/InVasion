package dk.sdu.se.f23.InVasion.collision;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.ProcessAt;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.commonbullet.Bullet;
import dk.sdu.se.f23.InVasion.commonenemy.Enemy;

public class CollisionDetector implements EntityProcessingService {

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            for (Entity bullet : world.getEntities(Bullet.class)) {
                LifePart entityLife = enemy.getPart(LifePart.class);

                // CollisionDetection
                if (this.collides(enemy, bullet) && enemy.getPart(LifePart.class) != null) {
                    // if entity has been hit, it's life should be reduced
                    if (entityLife.getLife() > 0) {
                        entityLife.setLife(entityLife.getLife() - 1);
                        //entityLife.setHit(true);
                        // if entity is out of life - remove
                        if (entityLife.getLife() <= 0) {
                            world.removeEntity(enemy);
                        }
                    }
                }
            }
        }
    }

    public boolean collides(Entity entity, Entity otherEntity) {
        PositionPart entityPart = entity.getPart(PositionPart.class);
        PositionPart otherEntityPart = otherEntity.getPart(PositionPart.class);

        int entityHeight = entity.getTexture().getHeight();
        int entityWidth = entity.getTexture().getWidth();
        int otherEntityHeight = otherEntity.getTexture().getHeight();
        int otherEntityWidth = otherEntity.getTexture().getWidth();

        return (entityPart.getX() < (otherEntityPart.getX() + otherEntityWidth) && (entityPart.getX() + entityWidth) > otherEntityPart.getX() &&
                entityPart.getY() < (otherEntityPart.getY() + otherEntityHeight) && (entityPart.getY() + entityHeight) > otherEntityPart.getY());
    }
}
