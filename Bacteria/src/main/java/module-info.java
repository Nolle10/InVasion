module Bacteria {
    uses dk.sdu.se.f23.InVasion.bacteria.services.ActionService;
    requires Common;
    requires shadedlibgdx;
    requires CommonEnemy;
    exports dk.sdu.se.f23.InVasion.bacteria;
    exports dk.sdu.se.f23.InVasion.bacteria.services;

    provides dk.sdu.se.f23.InVasion.common.services.EntityProcessingService with dk.sdu.se.f23.InVasion.bacteria.BacteriaControlSystem;
    provides dk.sdu.se.f23.InVasion.common.services.PluginService with dk.sdu.se.f23.InVasion.bacteria.BacteriaPlugin;
}