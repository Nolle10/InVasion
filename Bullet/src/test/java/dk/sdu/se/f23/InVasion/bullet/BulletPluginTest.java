package dk.sdu.se.f23.InVasion.bullet;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.EventListener;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;
import dk.sdu.se.f23.InVasion.common.events.events.FireShotEvent;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.commonbullet.Bullet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class BulletPluginTest {
    BulletPlugin bulletPlugin;
    GameData gameData;
    World world;

    @BeforeEach
    void setUp() {
        this.bulletPlugin = new BulletPlugin();
        this.gameData = mock(GameData.class);
        this.world = mock(World.class);
    }

    @Test
    void onEnable() {
        // Run the method to be tested
        this.bulletPlugin.onEnable(this.gameData, this.world);

        // Check correct behavior
        assertTrue(checkIfEventListenerIsRegistered(FireShotEvent.class));
        assertTrue(checkIfEventListenerIsRegistered(StateChangeEvent.class));
    }

    @Test
    void onDisable() {
        // Setup for test
        Bullet bullet1 = mock(Bullet.class);
        Bullet bullet2 = mock(Bullet.class);

        Entity notBullet1 = mock(Entity.class);

        ArrayList<Entity> entities = new ArrayList<>(List.of(bullet1, bullet2, notBullet1));

        when(this.world.getEntities()).thenReturn(entities);

        this.bulletPlugin.onEnable(this.gameData, this.world);

        System.out.println("We here");
        // Run the method to be tested
        this.bulletPlugin.onDisable(this.gameData, this.world);


        System.out.println("We here3");
        // Check correct behavior
        assertFalse(checkIfEventListenerIsRegistered(FireShotEvent.class));
        System.out.println("We here4");

        assertFalse(checkIfEventListenerIsRegistered(StateChangeEvent.class));
        System.out.println("We here5");

        verify(this.world, atLeastOnce()).removeEntity(bullet1);
        verify(this.world, atLeastOnce()).removeEntity(bullet2);
        verify(this.world, never()).removeEntity(notBullet1);
    }

    private static boolean checkIfEventListenerIsRegistered(Class<? extends Event> event) {
        for (EventListener eventListener2 : EventDistributor.getEventActivatorMap().get(event)) {
            if (eventListener2 instanceof BulletController) {
                return true;
            }
        }
        return false;
    }
}