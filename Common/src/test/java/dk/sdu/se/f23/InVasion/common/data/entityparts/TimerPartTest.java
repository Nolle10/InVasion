package dk.sdu.se.f23.InVasion.common.data.entityparts;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class TimerPartTest {
    private GameData data;
    private Entity entity;
    
    @BeforeEach
    void setUp() {
        data = mock(GameData.class);
        doNothing().when(data).setPlayerMoney(anyInt());
        when(data.getPlayerMoney()).thenReturn(1);
        entity = buildEntity();

    }

    @Test
    void processExpires() {
        setDelta(1.0f);

        TimerPart timerPart = new TimerPart(1);

        timerPart.process(this.data, entity);

        assertEquals(0, timerPart.getDuration());

        verify((LifePart)entity.getPart(LifePart.class), atLeastOnce()).setLife(0);
    }

    @Test
    void processLives() {
        setDelta(1.0f);

        TimerPart timerPart = new TimerPart(2);

        timerPart.process(this.data, entity);

        assertEquals(1, timerPart.getDuration());

        verify((LifePart)entity.getPart(LifePart.class), never()).setLife(anyInt());
    }

    private Entity buildEntity(){
        LifePart lifePart = mock(LifePart.class);
        doNothing().when(lifePart).setLife(anyInt());
        Entity entity = mock(Entity.class);
        when(entity.getPart(LifePart.class)).thenReturn(lifePart);
        return entity;
    }

    private void setDelta(float delta){
        when(this.data.getDelta()).thenReturn(delta);
    }
    
}