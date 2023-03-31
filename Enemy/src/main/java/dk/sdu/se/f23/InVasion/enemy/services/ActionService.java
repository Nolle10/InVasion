package dk.sdu.se.f23.InVasion.enemy.services;


import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.enemy.Enemy;

public interface ActionService {
    void calculate(GameData gameData, World world, Enemy enemy);
}