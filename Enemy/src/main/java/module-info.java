module Enemy {
    requires Common;
    requires com.badlogic.gdx;
    exports dk.sdu.se.f23.InVasion.enemy;

    provides dk.sdu.se.f23.InVasion.common.services.PluginService with dk.sdu.se.f23.InVasion.enemy.EnemyPlugin;
    provides dk.sdu.se.f23.InVasion.common.services.EntityProcessingService with dk.sdu.se.f23.InVasion.enemy.EnemyControlSystem;
}