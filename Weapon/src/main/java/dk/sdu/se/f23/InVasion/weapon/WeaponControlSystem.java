package dk.sdu.se.f23.InVasion.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.events.*;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.commonweapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class WeaponControlSystem implements EntityProcessingService, EventListener {
    private long lastShot = 0;
    private Point enemyPosition;
    private List<Entity> targets = new ArrayList<>();

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity weapon : world.getEntities(Weapon.class)) {
            lastShot += data.getDelta() * 50;
//TODO: Figure out why enemyPosition is null
            if (enemyPosition != null) {
                System.out.println("Fire weapon from "+ enemyPosition.getX() + " " + enemyPosition.getY());
                EventDistributor.sendEvent(new FireShotEvent(weapon, getDirection()), world);
                lastShot = 0;
            } else{
                System.out.println("No target");
            }
            PositionPart part = weapon.getPart(PositionPart.class);
            data.getSpriteBatch().draw(weapon.getTexture(), part.getX(), part.getY());
        }
    }

    private Entity createWeapon(Point position) {
        Entity weapon = new Weapon();
        weapon.add(new PositionPart(new Point(position.getX(), position.getY()), 0));
        weapon.setTexture(new Texture(Gdx.files.internal("Weapon/src/main/resources/TOWER.png")));
        return weapon;
    }

    private Point getDirection() {
        return enemyPosition;
    }

    @Override
    public void processEvent(Event event, World world) {
        if (event instanceof BuyTowerEvent) {
            world.addEntity(createWeapon(((BuyTowerEvent) event).getPosition()));
        }
        if (event instanceof TargetEvent) {
            targets.add(event.getSource());
            enemyPosition = ((TargetEvent) event).getTarget();
            findNearestNeighbor(world);
        }
    }

    private void findNearestNeighbor(World world){
        //Implementation of Nearest Neighbor algorithm
        for (Entity weapon : world.getEntities(Weapon.class)) {
            float weaponX = ((PositionPart) weapon.getPart(PositionPart.class)).getPos().getX();
            float weaponY = ((PositionPart) weapon.getPart(PositionPart.class)).getPos().getY();
            double closestDistance = Double.MAX_VALUE;
            Entity closestTarget = null;
            for (Entity target : targets) {
                if (!((LifePart) target.getPart(LifePart.class)).isDead()) {
                    float targetX = ((PositionPart) target.getPart(PositionPart.class)).getPos().getX();
                    float targetY = ((PositionPart) target.getPart(PositionPart.class)).getPos().getY();
                    double distance = Math.sqrt(Math.pow(weaponX - targetX, 2) + Math.pow(weaponY - targetY, 2));
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestTarget = target;
                    }
                }
            }
            if (closestTarget != null) {
                float closestX = ((PositionPart) closestTarget.getPart(PositionPart.class)).getPos().getX();
                float closestY = ((PositionPart) closestTarget.getPart(PositionPart.class)).getPos().getY();
                System.out.println("Closest target: " + closestX + " " + closestY);
                if (enemyPosition == null) {
                    enemyPosition = new Point((int) closestX, (int) closestY);
                }
                enemyPosition.setX((int) closestX);
                enemyPosition.setY((int) closestY);
            }
        } cleanEnemies();
    }

    private void cleanEnemies() {
        targets.removeIf(e -> ((LifePart) e.getPart(LifePart.class)).isDead());
    }
}

