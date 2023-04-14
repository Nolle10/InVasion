package dk.sdu.se.f23.InVasion.player;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;

public class Player extends Entity {
    //player is a singleton

    private static Player instance = new Player();
    private Player(){

    }
    public static Player getInstance(){
        return instance;
    }
}
