package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;

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