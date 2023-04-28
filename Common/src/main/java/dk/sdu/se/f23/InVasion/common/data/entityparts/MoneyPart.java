package dk.sdu.se.f23.InVasion.common.data.entityparts;

import com.badlogic.gdx.Game;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;

/**
 * @author Freja Madsen (Supervised by Oliver Svendsen)
 * @see dk.sdu.se.f23.InVasion.common.data.entityparts.EntityPart
 */
public class MoneyPart implements EntityPart {

    private int money;

    /**
     * @param money - what amount of money the player gets for killing this enemy
     */
    public MoneyPart(int money){
        this.money = money;
    }

    /**
     * This method checks if the enemy is dead and if it is, money is added to the
     * players balance
     * @param data
     * @param entity
     */
    @Override
    public void process(GameData data, Entity entity) {
        LifePart enemyLifePart = entity.getPart(LifePart.class);

        if(enemyLifePart.getLife() <= 0 && enemyLifePart.isNotDead()){
            int balance = data.getPlayerMoney() + getMoney();
            data.setPlayerMoney(balance);
        }

    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
