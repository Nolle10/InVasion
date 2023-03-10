package data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * For any problems || questions please contact:
 * @author Oliver
 */
class WorldTest {

    @Test
    void addEntity() {
        Entity expectedEntity = new Entity();
        World world = new World();

        String ID = world.addEntity(expectedEntity);

        Entity actualEntity = world.getEntity(ID);

        Assertions.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void removeEntity() {
        Map<String, Entity> entityMap = new ConcurrentHashMap<>();

        Entity entity1 = new Entity();
        Entity entity2 = new Entity();
        World world = new World();

        world.addEntity(entity1);
        world.addEntity(entity2);
        entityMap.put(entity1.getID(), entity1);

        world.removeEntity(entity2);

        Assertions.assertEquals(entityMap.get(entity1.getID()), world.getEntity(entity1.getID()));

    }
}