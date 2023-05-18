package dk.sdu.se.f23.InVasion.managers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GameStateManagerTest {

    @Test
    void setState() {
        GameStateManager actualGameState = mock(GameStateManager.class);

        actualGameState.setState(3);

        assertEquals(3, actualGameState.getCurrentState());

    }
}