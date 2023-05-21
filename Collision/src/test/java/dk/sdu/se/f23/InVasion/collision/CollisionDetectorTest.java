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
import static org.mockito.Mockito.*;

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

    @SuppressWarnings("unchecked")
    @Test
    void processHits() {
        Entity mockEnemy = createMockEntity(Enemy.class, "1", 2, 0, 0, 10, 10);
        Entity mockBullet = createMockEntity(Bullet.class, "3", 2, 3, 3, 10, 10);

        when(this.mockedWorld.getEntities(Bullet.class)).thenReturn(List.of(mockBullet));
        when(this.mockedWorld.getEntities(Enemy.class)).thenReturn(List.of(mockEnemy));

        this.collisionDetector.process(this.mockedGameData, this.mockedWorld);

        // Check that enemy life is changed
        verify((LifePart)mockEnemy.getPart(LifePart.class), atLeastOnce()).setHit(true);


        // Check bullet is removed after hit
        verify(mockedWorld, atLeastOnce()).removeEntity(mockBullet);
    }

    @SuppressWarnings("unchecked")
    @Test
    void processMiss() {
        Entity mockEnemy = createMockEntity(Enemy.class, "1", 1, 0, 0, 10, 10);
        Entity mockBullet = createMockEntity(Bullet.class, "3", 2, 300, 300, 10, 10);

        when(this.mockedWorld.getEntities(Bullet.class)).thenReturn(List.of(mockBullet));
        when(this.mockedWorld.getEntities(Enemy.class)).thenReturn(List.of(mockEnemy));

        this.collisionDetector.process(this.mockedGameData, this.mockedWorld);

        // Check that enemy life is changed
        verify((LifePart)mockEnemy.getPart(LifePart.class), never()).setLife(anyInt());

        // Check enemy NOT removed
        verify(mockedWorld, never()).removeEntity(mockEnemy);

        // Check bullet NOT removed
        verify(mockedWorld, never()).removeEntity(mockBullet);
    }

    @SuppressWarnings("unchecked")
    @Test
    void processTwoBulletsCollidesNotProcessed() {
        Entity mockBullet1 = createMockEntity(Bullet.class, "1", 2, 0, 0, 10, 10);
        Entity mockBullet2 = createMockEntity(Bullet.class, "3", 2, 3, 3, 10, 10);

        when(this.mockedWorld.getEntities(Bullet.class)).thenReturn(List.of(mockBullet1, mockBullet2));
        when(this.mockedWorld.getEntities(Enemy.class)).thenReturn(List.of());

        this.collisionDetector.process(this.mockedGameData, this.mockedWorld);

        // Check bullets actually collides
        assertTrue(this.collisionDetector.collides(mockBullet1, mockBullet2));

        // Check that bullet 1 is not removed
        verify(mockedWorld, never()).removeEntity(mockBullet1);

        // Check that bullet 2 is not removed
        verify(mockedWorld, never()).removeEntity(mockBullet2);
    }

    @SuppressWarnings("unchecked")
    @Test
    void processTwoEnemiesCollidesNotProcessed() {
        Entity mockEnemy1 = createMockEntity(Enemy.class, "1", 2, 0, 0, 10, 10);
        Entity mockEnemy2 = createMockEntity(Enemy.class, "3", 2, 3, 3, 10, 10);

        when(this.mockedWorld.getEntities(Bullet.class)).thenReturn(List.of());
        when(this.mockedWorld.getEntities(Enemy.class)).thenReturn(List.of(mockEnemy1, mockEnemy2));

        this.collisionDetector.process(this.mockedGameData, this.mockedWorld);

        // Check enemies actually collides
        assertTrue(this.collisionDetector.collides(mockEnemy1, mockEnemy2));

        // Check that bullet 1 is not removed
        verify(mockedWorld, never()).removeEntity(mockEnemy1);

        // Check that bullet 2 is not removed
        verify(mockedWorld, never()).removeEntity(mockEnemy2);
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
        doAnswer(newLife ->{
            when(lifePart.getLife()).thenReturn(newLife.getArgument(0));
            return null;
        }).when(lifePart).setLife(anyInt());

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