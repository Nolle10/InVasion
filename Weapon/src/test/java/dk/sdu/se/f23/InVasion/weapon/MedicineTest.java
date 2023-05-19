package dk.sdu.se.f23.InVasion.weapon;

import com.badlogic.gdx.graphics.Texture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MedicineTest {
    private final String name = "Medicin";
    private final Texture texture = mock(Texture.class);
    private final int price = 10;
    private Medicine medicine;
    @BeforeEach
    void setUp() {
        medicine = new Medicine(name, texture, price);
    }

    @Test
    void getName() {
        assertEquals(medicine.getName(), name);
    }

    @Test
    void getTexture() {
        assertEquals(medicine.getTexture(), texture);

    }

    @Test
    void getPrice() {
        assertEquals(medicine.getPrice(), price);

    }
}