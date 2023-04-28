package dk.sdu.se.f23.InVasion.ai;


import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.enemy.AIType;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.enemy.Enemy;
import dk.sdu.se.f23.InVasion.enemy.services.ActionService;

import java.util.ArrayList;
import java.util.List;


public class AIAction implements ActionService {

    private AIType aiType = AIType.STUPID;

    @Override
    public List<Point> calculate(GameData gameData, World world, Enemy enemy) {

        //TEST ROUTE - SHOULD BE REMOVED
        List<Point> route = new ArrayList<>();
        route.add(new Point(0,0));
        route.add(new Point(0,1));
        route.add(new Point(0,3));
        route.add(new Point(0,5));
        return route;
    }

    @Override
    public AIType getAiType() {
        return aiType;
    }
}
