package data.entityparts;

import data.GameData;
import data.World;

public interface EntityPart {
    void process(GameData data, World world);
}
