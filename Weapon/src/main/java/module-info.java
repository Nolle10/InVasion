module Weapon {
    requires Common;
    requires shadedlibgdx;

    provides dk.sdu.se.f23.InVasion.common.services.PluginService with dk.sdu.se.f23.InVasion.weapon.WeaponPlugin;
}