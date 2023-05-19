package dk.sdu.se.f23.InVasion.common.data.entityparts;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;

public class MoneyPart implements EntityPart {

    private int money;

    /**
     * @param money - The amount of money the player gets for killing this enemy
     */
    public MoneyPart(int money) {
        this.money = money;
    }

    /**
     * This method checks if the enemy is dead, if so, money is added to the
     * players balance
     *
     * @param data
     * @param entity
     */
    @Override
    public void process(GameData data, Entity entity) {
        LifePart enemyLifePart = entity.getPart(LifePart.class);

        if (enemyLifePart.isDead()) {
            data.addMoney(getMoney());
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
