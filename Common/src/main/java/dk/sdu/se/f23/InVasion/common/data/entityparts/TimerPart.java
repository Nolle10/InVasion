package dk.sdu.se.f23.InVasion.common.data.entityparts;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;

import java.util.List;


public class TimerPart implements EntityPart{
    private float expiration;

    public TimerPart(float expiration){
        this.expiration = expiration;
    }
    @Override
    public void process(GameData data, Entity entity) {
        if(expiration > 0){
            reduceExpiration(data.getDelta());
        }
        if(expiration <= 0){
            LifePart lifePart = entity.getPart(LifePart.class);
            lifePart.setLife(0);
        }

    }
    private void reduceExpiration(float amount){
        this.expiration -= amount;

    }
    public float getExpiration() {
        return expiration;
    }

}
