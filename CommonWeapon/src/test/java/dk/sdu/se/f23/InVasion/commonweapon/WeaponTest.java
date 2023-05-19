package dk.sdu.se.f23.InVasion.commonweapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {
    private Weapon weapon;
    @BeforeEach
    void setUp() {
        this.weapon = new Weapon();
    }

    @Test
    void shouldNotShoot() {
        assertFalse(this.weapon.shouldShoot(0.0f));
    }
    @Test
    void shouldShoot() {
        assertTrue(this.weapon.shouldShoot(99.0f));
    }
}