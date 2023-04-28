package dk.sdu.se.f23.InVasion.common.data.entityparts;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;


public class TimerPart implements EntityPart{
    private float duration;

    public TimerPart(float duration){
        this.duration = duration;
    }
    @Override
    public void process(GameData data, Entity entity) {
        if(duration > 0){
            reduceDuration(data.getDelta());
        }
        if(duration <= 0){
            LifePart lifePart = entity.getPart(LifePart.class);
            lifePart.setLife(0);
        }

    }
    private void reduceDuration(float amount){
        this.duration -= amount;

    }
    public float getDuration() {
        return duration;
    }

}
