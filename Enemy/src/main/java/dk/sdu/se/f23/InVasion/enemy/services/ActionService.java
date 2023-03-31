package dk.sdu.se.f23.InVasion.enemy.services;

import data.GameData;
import data.World;
import dk.sdu.se.f23.InVasion.enemy.Enemy;

public interface ActionService {
    void calculate(GameData gameData, World world, Enemy enemy);
}