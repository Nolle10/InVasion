package dk.sdu.se.f23.InVasion.common.data.entityparts;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MoneyPartTest {
    private GameData data;
    private MoneyPart moneyPart;

    @BeforeEach
    void setUp() {
        this.moneyPart = new MoneyPart(1);
        data = mock(GameData.class);
        doNothing().when(data).setPlayerMoney(anyInt());
        when(data.getPlayerMoney()).thenReturn(1);
    }

    @Test
    void processEntityIsDead() {
        Entity entity = buildEntity(true);
        this.moneyPart.process(this.data, entity);

        verify((LifePart) entity.getPart(LifePart.class), atLeastOnce()).isDead();
        verify(this.data, atLeastOnce()).addMoney(anyInt());
    }

    @Test
    void processEntityNotDead() {
        Entity entity = buildEntity(false);
        this.moneyPart.process(this.data, entity);

        verify((LifePart) entity.getPart(LifePart.class), atLeastOnce()).isDead();
        verify(this.data, never()).setPlayerMoney(anyInt());
    }

    private Entity buildEntity(boolean lifePartIsDead){
        LifePart lifePart = mock(LifePart.class);
        when(lifePart.isDead()).thenReturn(lifePartIsDead);
        Entity entity = mock(Entity.class);
        when(entity.getPart(LifePart.class)).thenReturn(lifePart);
        return entity;
    }
}