package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;

public class MainScreenState extends GameState{

    private Stage stage;
    private TextButton button;
    private TextButton.TextButtonStyle textButtonStyle;

    public MainScreenState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.WHITE;
        button = new TextButton("Play game button", textButtonStyle);
        button.setPosition(900,500);
        button.addListener( new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Start button clicked!");
                gsm.setState(1);
                return true;
            }
        });

        stage.addActor(button);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw() {
        stage.draw();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }
}
