module Bullet {
    requires Common;
    exports dk.sdu.se.f23.InVasion.bullet;

    provides dk.sdu.se.f23.InVasion.common.services.PluginService with dk.sdu.se.f23.InVasion.bullet.BulletPlugin;
}