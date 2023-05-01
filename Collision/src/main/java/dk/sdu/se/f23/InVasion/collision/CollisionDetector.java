package dk.sdu.se.f23.InVasion.collision;

import dk.sdu.se.f23.InVasion.bullet.Bullet;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.ProcessAt;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.enemy.Enemy;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class CollisionDetector implements EntityProcessingService {

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        // two for loops for all entities in the world
        for (Entity entity : world.getEntities()) {
            for (Entity otherEntity : world.getEntities()) {
                // get life parts on all entities
                LifePart entityLife = entity.getPart(LifePart.class);

                // if the two entities are identical, skip the iteration
                if (entity.getID().equals(otherEntity.getID())) {
                    continue;
                }

                // CollisionDetection
                if (this.collides(entity, otherEntity)) {
                    // if entity has been hit, and should have its life reduced
                    if (entityLife.getLife() > 0) {
                        entityLife.setLife(entityLife.getLife() - 1);
                        entityLife.setHit(true);
                        // if entity is out of life - remove
                        if (entityLife.getLife() <= 0) {
                            world.removeEntity(entity);
                        }
                    }
                }
            }
        }
    }

    public Boolean collides(Entity entity, Entity otherEntity) {
        //if (entity instanceof Enemy || entity instanceof Bullet) {
            PositionPart entMov = entity.getPart(PositionPart.class);
            PositionPart entMov2 = otherEntity.getPart(PositionPart.class);
            float dx = (float) entMov.getX() - (float) entMov2.getX();
            float dy = (float) entMov.getY() - (float) entMov2.getY();
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            int entityHeigth = entity.getTexture().getHeight();
            int entityWidth = entity.getTexture().getWidth();
            int otherEntityHeigth = otherEntity.getTexture().getHeight();
            int otherEntityWidth = otherEntity.getTexture().getWidth();
            float hitSquareEntity = entityHeigth*entityWidth; //(xt,yt - xt,yb) * (xt,yb - xb,yb) = area/hit square
            float hitSquareOtherEntity = otherEntityHeigth*otherEntityHeigth;
            if (distance < (hitSquareEntity + hitSquareOtherEntity)){
            //if (distance < ((entity.getHitShapeX()[entity.getHitShapeX().length - 1] - entity.getHitShapeX()[0]) + (entity.getHitShapeX()[otherEntity.getHitShapeX().length - 1] - otherEntity.getHitShapeX()[0]))) {
                return true;
            }

            return false;
        /*}
        return null;*/
    }
}
