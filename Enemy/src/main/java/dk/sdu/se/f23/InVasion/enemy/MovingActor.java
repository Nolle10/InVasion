package dk.sdu.se.f23.InVasion.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MovingActor extends Actor {

    private Action runningAction;
    private float speed = 2f;

    public void moveTo(Vector2 location) {
        runningAction = Actions.moveTo(location.x, location.y, speed);
        this.addAction(runningAction);
    }

    public void stopAction() {
        this.removeAction(runningAction);
    }

    public void stopAllActions() {
        this.clearActions();
    }

}

