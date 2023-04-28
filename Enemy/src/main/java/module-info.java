module Enemy {
    uses dk.sdu.se.f23.InVasion.enemy.services.ActionService;
    requires Common;
    requires shadedlibgdx;
    exports dk.sdu.se.f23.InVasion.enemy;
    exports dk.sdu.se.f23.InVasion.enemy.services;

    provides dk.sdu.se.f23.InVasion.common.services.EntityProcessingService with dk.sdu.se.f23.InVasion.enemy.EnemyControlSystem;
    provides dk.sdu.se.f23.InVasion.common.services.PluginService with dk.sdu.se.f23.InVasion.enemy.EnemyPlugin;
}