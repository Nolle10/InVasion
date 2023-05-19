module AI {
    requires Enemy;
    requires Common;

    provides dk.sdu.se.f23.InVasion.enemy.services.ActionService with dk.sdu.se.f23.InVasion.ai.Astar;
}