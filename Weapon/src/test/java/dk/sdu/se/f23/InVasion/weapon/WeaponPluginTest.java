package dk.sdu.se.f23.InVasion.weapon;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.shop.BuyableManager;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.EventListener;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;
import dk.sdu.se.f23.InVasion.common.events.events.BuyTowerEvent;
import dk.sdu.se.f23.InVasion.common.events.events.TargetEvent;
import dk.sdu.se.f23.InVasion.commonweapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


class WeaponPluginTest {
    private WeaponPlugin weaponPlugin;
    private GameData gameData;
    private World world;

    @BeforeEach
    void setUp() {
        Gdx.files = mock(Lwjgl3Files.class);
        spy(Gdx.files);
        when(Gdx.files.internal(anyString())).thenReturn(mock(FileHandle.class));
        when(new Texture(any(FileHandle.class))).thenReturn(mock(Texture.class));
        this.weaponPlugin = new WeaponPlugin();
        this.gameData = mock(GameData.class);
        this.world = mock(World.class);
    }

    @Test
    void onEnable() {
        weaponPlugin.onEnable(gameData, world);
    }

    @Test
    void onDisable() {
        // Setup for test
        Weapon weapon1 = mock(Weapon.class);
        Weapon weapon2 = mock(Weapon.class);

        Entity notWeapon1 = mock(Entity.class);

        ArrayList<Entity> entities = new ArrayList<>(List.of(weapon1, weapon2, notWeapon1));
        when(this.world.getEntities()).thenReturn(entities);

        weaponPlugin.onEnable(this.gameData, this.world);


        weaponPlugin.onDisable(this.gameData, this.world);


        // Check correct behavior
        assertFalse(checkIfEventListenerIsRegistered(BuyTowerEvent.class));
        assertFalse(checkIfEventListenerIsRegistered(TargetEvent.class));

        verify(this.world, atLeastOnce()).removeEntity(weapon1);
        verify(this.world, atLeastOnce()).removeEntity(weapon2);
        verify(this.world, never()).removeEntity(notWeapon1);
    }

    private static boolean checkIfEventListenerIsRegistered(Class<? extends Event> event) {
        for (EventListener eventListener2 : EventDistributor.getEventActivatorMap().get(event)) {
            if (eventListener2 instanceof WeaponControlSystem) {
                return true;
            }
        }
        return false;
    }
}