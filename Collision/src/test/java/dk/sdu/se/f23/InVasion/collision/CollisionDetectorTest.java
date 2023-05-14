package dk.sdu.se.f23.InVasion.collision;

import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.commonbullet.Bullet;
import dk.sdu.se.f23.InVasion.commonenemy.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CollisionDetectorTest {
    private CollisionDetector collisionDetector;
    private GameData mockedGameData;
    private World mockedWorld;


    @BeforeEach
    void setUp(){
        this.collisionDetector = new CollisionDetector();
        this.mockedGameData = mock(GameData.class);
        this.mockedWorld = mock(World.class);
    }

    @Test
    void processHitsLives() {
        Entity mockEnemy = createMockEntity(Enemy.class, "1", 2, 0, 0, 10, 10);
        Entity mockBullet = createMockEntity(Bullet.class, "3", 2, 3, 3, 10, 10);



    }

    @Test
    void collides() {
        Entity mockEntity1 = createMockEntity(Enemy.class, "1", 2, 0, 0, 10, 10);
        Entity mockEntity2 = createMockEntity(Entity.class, "2", 2, 100, 100, 30, 30);
        Entity mockEntity3 = createMockEntity(Bullet.class, "3", 2, 3, 3, 10, 10);
        Entity mockEntity4 = createMockEntity(Bullet.class, "3", 2, 120, 120, 30, 30);

        assertFalse(collisionDetector.collides(mockEntity1, mockEntity2));
        assertFalse(collisionDetector.collides(mockEntity3, mockEntity4));
        assertTrue(collisionDetector.collides(mockEntity1, mockEntity3));
        assertTrue(collisionDetector.collides(mockEntity2, mockEntity4));
    }

    private Entity createMockEntity(Class<? extends Entity> entityType, String entityID, int entityLife, float entityX, float entityY, int height, int width) {
        // Setup mock objects (Entity and EntityParts)
        Entity entity = mock(entityType);
        LifePart lifePart = mock(LifePart.class);
        PositionPart positionPart = mock(PositionPart.class);

        // Setup Entity behavior
        when(entity.getID()).thenReturn(entityID);

        when(entity.getPart(LifePart.class)).thenReturn(lifePart);
        when(entity.getPart(PositionPart.class)).thenReturn(positionPart);

        // Setup LifePart behavior
        when(lifePart.getLife()).thenReturn(entityLife);

        // Setup PositionPart behavior
        when(positionPart.getX()).thenReturn(entityX);
        when(positionPart.getX()).thenReturn(entityY);

        Texture texture = mock(Texture.class);
        when(texture.getHeight()).thenReturn(height);
        when(texture.getWidth()).thenReturn(width);

        when(entity.getTexture()).thenReturn(texture);

        return entity;
    }

    @SuppressWarnings("unchecked")
    private static World generateMockedWorld(Enemy enemy, Bullet bullet) {
        World mockWorld = mock(World.class);

        when(mockWorld.getEntities(Entity.class)).thenReturn(List.of(enemy));
        when(mockWorld.getEntities(Bullet.class)).thenReturn(List.of(bullet));
        return mockWorld;
    }

}