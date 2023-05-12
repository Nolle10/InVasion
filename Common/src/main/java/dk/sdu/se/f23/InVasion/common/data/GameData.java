package dk.sdu.se.f23.InVasion.common.data;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;

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
    private GameStateEnum currentState;
    private List<Event> events = new CopyOnWriteArrayList<>();

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

    public GameStateEnum getCurrentState(){return currentState;}

    public void setCurrentState(GameStateEnum state){ this.currentState = state;}

    public void addEvent(Event e) {
        events.add(e);
    }

    public void removeEvent(Event e) {
        events.remove(e);
    }

    public List<Event> getEvents() {
        return events;
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

    public <E extends Event> List<Event> getEvents(Class<E> type, String sourceID) {
        List<Event> r = new ArrayList();
        for (Event event : events) {
            if (event.getClass().equals(type)) {
                r.add(event);
            }
        }

        return r;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }
}
