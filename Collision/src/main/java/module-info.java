import dk.sdu.se.f23.InVasion.collision.CollisionDetector;

module Collision {
    requires Common;
    requires shadedlibgdx;
    requires CommonEnemy;
    requires CommonBullet;

    provides dk.sdu.se.f23.InVasion.common.services.EntityProcessingService with CollisionDetector;
}