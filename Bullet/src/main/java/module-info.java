module Bullet {
    requires Common;
    exports dk.sdu.se.f23.InVasion.bullet;
    requires shadedlibgdx;
    requires CommonBullet;

    provides dk.sdu.se.f23.InVasion.common.services.EntityProcessingService with dk.sdu.se.f23.InVasion.bullet.BulletController;
    provides dk.sdu.se.f23.InVasion.common.services.PluginService with dk.sdu.se.f23.InVasion.bullet.BulletPlugin;
}