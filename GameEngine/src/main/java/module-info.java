import dk.sdu.se.f23.InVasion.common.services.PluginService;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;

module GameEngine {
    requires Common;
    requires java.desktop;
    requires com.badlogic.gdx;
    uses PluginService;
    uses EntityProcessingService;
}