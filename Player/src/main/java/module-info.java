module Player {
    requires Common;
    requires shadedlibgdx;

    provides dk.sdu.se.f23.InVasion.common.services.PluginService with dk.sdu.se.f23.InVasion.player.PlayerPlugin;
    provides dk.sdu.se.f23.InVasion.common.services.EntityProcessingService with dk.sdu.se.f23.InVasion.player.PlayerControlSystem;
}