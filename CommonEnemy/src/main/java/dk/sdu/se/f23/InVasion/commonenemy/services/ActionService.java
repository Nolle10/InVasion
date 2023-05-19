package dk.sdu.se.f23.InVasion.commonenemy.services;


import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.commonenemy.AIType;

import java.util.List;

public interface ActionService {
    List<Point> calculate(World world);
    AIType getAiType();
}