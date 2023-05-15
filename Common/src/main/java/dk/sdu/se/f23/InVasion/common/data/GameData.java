package dk.sdu.se.f23.InVasion.common.data;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameData {
    private int playerMoney;
    private float delta;
    private int displayWidth;
    private int displayHeight;
    private SpriteBatch spriteBatch;
    private InputMultiplexer multiplexer;

    //private final GameKeys keys = new GameKeys();

    public void setMultiplexer(InputMultiplexer inputMultiplexer){
        this.multiplexer = inputMultiplexer;
    }

    public void addProcessor(InputProcessor p){
        multiplexer.addProcessor(p);
    }

    public void removeProcessor(InputProcessor p){
        multiplexer.removeProcessor(p);
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public void setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    /*TODO: Create custom game keys for our specific game
    public GameKeys getKeys() {
        return keys;
    }*/

    public void setDelta(float delta) {
        this.delta = delta;
    }

    public float getDelta() {
        return delta;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }
}
