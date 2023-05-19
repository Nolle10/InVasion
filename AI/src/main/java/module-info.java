import dk.sdu.se.f23.InVasion.commonenemy.services.ActionService;

module AI {
    requires Common;
    requires CommonEnemy;

    provides ActionService with dk.sdu.se.f23.InVasion.ai.Astar;

}