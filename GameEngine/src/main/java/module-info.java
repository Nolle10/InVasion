import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.common.services.PluginService;

module GameEngine {
    requires Common;
    requires java.desktop;
    requires shadedlibgdx;
    uses PluginService;
    uses EntityProcessingService;
}