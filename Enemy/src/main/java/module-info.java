module Enemy {
    uses dk.sdu.se.f23.InVasion.commonenemy.services.ActionService;
    requires Common;
    requires shadedlibgdx;
    requires CommonEnemy;

    provides dk.sdu.se.f23.InVasion.common.services.EntityProcessingService with dk.sdu.se.f23.InVasion.enemy.EnemyControlSystem;
    provides dk.sdu.se.f23.InVasion.common.services.PluginService with dk.sdu.se.f23.InVasion.enemy.EnemyPlugin;
}