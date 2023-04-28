package dk.sdu.se.f23.InVasion.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.se.f23.InVasion.common.data.*;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.events.BuyTowerEvent;
import dk.sdu.se.f23.InVasion.common.events.Event;
import dk.sdu.se.f23.InVasion.common.events.EventListener;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.commonweapon.Weapon;

public class WeaponControlSystem implements EntityProcessingService, EventListener {

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity weapon : world.getEntities(Weapon.class)) {
        }
    }

    private Entity createWeapon(Point position){
        Entity weapon = new Weapon();
        weapon.add(new PositionPart(new Point(position.getX(),position.getY()),0));
        weapon.setTexture(new Texture(Gdx.files.internal("Weapon/src/main/resources/TOWER.png")));
        return weapon;
    }

    @Override
    public void processEvent(Event event, World world) {
        if (event instanceof BuyTowerEvent) {
            world.addEntity(createWeapon(((BuyTowerEvent) event).getPosition()));
        }
    }
}
