package dk.sdu.se.f23.InVasion.bacteria.services;


import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.bacteria.AIType;

import java.util.List;

public interface ActionService {
    List<Point> calculate(World world);
    AIType getAiType();
}