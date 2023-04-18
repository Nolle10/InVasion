package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;

public class PauseState extends GameState{

    private Stage stage;
    /*
    private TextButton button;
    private TextButton button1;
    private TextButton.TextButtonStyle textButtonStyle;*/

    public PauseState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
/*
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.GREEN;
        button = new TextButton("Back to game button", textButtonStyle);
        button.setPosition(900,800);
        button.addListener( new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Back to game button clicked!");
                gsm.setState(2);
                return true;
            }
        });
        button1 = new TextButton("Back to main menu button", textButtonStyle);
        button1.setPosition(900,700);
        button1.addListener( new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Back to menu button clicked!");
                gsm.setState(0);
                return true;
            }
        });

        stage.addActor(button);
        stage.addActor(button1);*/
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
