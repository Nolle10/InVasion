package bulletpackage;

import data.Entity;
import data.GameData;

public interface BulletSPI {
    Entity createBullet(Entity e, GameData gameData);
}
