import dk.sdu.se.f23.InVasion.commonenemy.services.ActionService;

module Bacteria {
    uses ActionService;
    requires Common;
    requires shadedlibgdx;
    requires CommonEnemy;

    provides dk.sdu.se.f23.InVasion.common.services.EntityProcessingService with dk.sdu.se.f23.InVasion.bacteria.BacteriaControlSystem;
    provides dk.sdu.se.f23.InVasion.common.services.PluginService with dk.sdu.se.f23.InVasion.bacteria.BacteriaPlugin;
}