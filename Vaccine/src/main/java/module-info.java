module Vaccine {
    requires Common;
    requires shadedlibgdx;
    requires CommonWeapon;
    requires CommonEnemy;

    provides dk.sdu.se.f23.InVasion.common.services.ShopPluginService with dk.sdu.se.f23.InVasion.vaccine.VaccinePlugin;
    provides dk.sdu.se.f23.InVasion.common.services.EntityProcessingService with dk.sdu.se.f23.InVasion.vaccine.VaccineControlSystem;
}