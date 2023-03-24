package data.entityparts;

import data.Entity;
import data.GameData;
import data.World;

public interface EntityPart {
    void process(GameData data, Entity entity);
}
