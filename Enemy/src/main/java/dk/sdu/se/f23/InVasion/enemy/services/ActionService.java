package dk.sdu.se.f23.InVasion.enemy.services;


import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.enemy.AIType;

import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.enemy.Enemy;

import java.util.List;

public interface ActionService {
    List<Point> calculate(GameData gameData, World world, Enemy enemy);
    AIType getAiType();
}