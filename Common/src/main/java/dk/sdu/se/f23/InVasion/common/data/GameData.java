package dk.sdu.se.f23.InVasion.common.data;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameData {
    private int playerMoney = 0;
    private int waveCount = 0;
    private float delta;
    private int displayWidth;
    private int displayHeight;
    private SpriteBatch spriteBatch;
    private InputMultiplexer multiplexer;

    public void setMultiplexer(InputMultiplexer inputMultiplexer) {
        this.multiplexer = inputMultiplexer;
    }

    public void addProcessor(InputProcessor p) {
        multiplexer.addProcessor(p);
    }

    public void removeProcessor(InputProcessor p) {
        multiplexer.removeProcessor(p);
    }

    public void removeAllProccessors() {
        for (int i = 0; i < multiplexer.getProcessors().size; i++) {
            multiplexer.removeProcessor(0);
        }
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public void setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

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

    public void addMoney(int amount) {
        setPlayerMoney(getPlayerMoney() + amount);
    }

    public void subtractMoney(int amount){
        setPlayerMoney(getPlayerMoney() - amount);
    }

    public int getWaveCount() {
        return waveCount;
    }

    public void setWaveCount(int waveCount) {
        this.waveCount = waveCount;
    }
}
