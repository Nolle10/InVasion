module Map {
    requires Common;
    requires java.desktop;
    requires shadedlibgdx;

    provides dk.sdu.se.f23.InVasion.common.services.PluginService with dk.sdu.se.f23.InVasion.map.MapPlugin;
}