import dk.sdu.se.f23.InVasion.collision.CollisionDetector;

module Collision {
    requires Common;
    requires Bullet;
    requires Enemy;
    requires shadedlibgdx;
    requires Player;

    provides dk.sdu.se.f23.InVasion.common.services.EntityProcessingService with CollisionDetector;
}