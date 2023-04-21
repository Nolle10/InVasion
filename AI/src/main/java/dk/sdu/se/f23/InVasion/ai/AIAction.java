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
        /*PositionPart positionPart = enemy.getPart(PositionPart.class);
        MovingPart movingPart = enemy.getPart(MovingPart.class);
        Point point;
        // calculate direction and distance to player base
        float dx = world.getGoalState().getX() - positionPart.getPos().getX();
        float dy = world.getGoalState().getY() - positionPart.getPos().getY();
        float distance = (float) Math.sqrt(dx*dx + dy*dy);
        dx /= distance;
        dy /= distance;

        // set velocity
        movingPart.setPos(new Point((int) (dx * movingPart.getSpeed()), (int) (dy * movingPart.getSpeed())));*/

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
