package dk.sdu.se.f23.InVasion.AI;

import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.enemy.AIType;
import dk.sdu.se.f23.InVasion.enemy.Enemy;
import dk.sdu.se.f23.InVasion.enemy.services.ActionService;

import java.util.List;

public class AStarAction implements ActionService  {
    AIType aiType = AIType.A_STAR;

    @Override
    public List<Point> calculate(GameData gameData, World world, Enemy enemy) {
        return null;
    }

    public AIType getAiType() {
        return aiType;
    }
}

