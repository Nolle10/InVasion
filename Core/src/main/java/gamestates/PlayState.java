package gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import managers.GameStateManager;

public class PlayState extends GameState {

    private ShapeRenderer sr;

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {

        sr = new ShapeRenderer();
    }

    public void update(float dt) {

        handleInput();

    }

    public void draw() {
    }

    public void handleInput() {
    }

    public void dispose() {
    }
}