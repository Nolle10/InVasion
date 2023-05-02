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
import dk.sdu.se.f23.InVasion.player.Player;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class CollisionDetector implements EntityProcessingService {

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        // two for loops for all entities in the world
        //FIXME make sure at collision only happens when a bullet and an enemy collides
        for (Entity entity : world.getEntities(Player.class)) { //Entity enemy : world.getEntities(Enemy.class)
            for (Entity otherEntity : world.getEntities(Bullet.class)) { //Entity bullet : world.getEntities(Bullet.class)
                // get life parts on all entities
                LifePart entityLife = entity.getPart(LifePart.class);

                // if the two entities are identical, skip the iteration
                if (entity.getID().equals(otherEntity.getID())) {
                    continue;
                }

                // CollisionDetection
                if (this.collides(entity, otherEntity) && entity.getPart(LifePart.class) != null) {
                    // if entity has been hit, and should have its life reduced
                    System.out.println("collision");
                    if (entityLife.getLife() > 0) {
                        entityLife.setLife(entityLife.getLife() - 1);
                        entityLife.setHit(true);
                        // if entity is out of life - remove
                        if (entityLife.getLife() <= 0) {
                            world.removeEntity(entity);
                        }
                    }else {
                        continue;
                    }
                }
            }
        }
    }

    public Boolean collides(Entity entity, Entity otherEntity) {
            PositionPart entMov = entity.getPart(PositionPart.class);
            PositionPart entMov2 = otherEntity.getPart(PositionPart.class);
            float dx = entMov.getX() - entMov2.getX();
            float dy = entMov.getY() - entMov2.getY();
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            int entityHeigth = entity.getTexture().getHeight();
            int entityWidth = entity.getTexture().getWidth();
            int otherEntityHeigth = otherEntity.getTexture().getHeight();
            int otherEntityWidth = otherEntity.getTexture().getWidth();
            float hitSquareEntity = entityHeigth*entityWidth; //(xt,yt - xt,yb) * (xt,yb - xb,yb) = area/hit square
            float hitSquareOtherEntity = otherEntityHeigth*otherEntityWidth;
            if (distance < (hitSquareEntity + hitSquareOtherEntity)){
                return true;
            }

            return false;
    }
}
