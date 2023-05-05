package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;
import dk.sdu.se.f23.InVasion.common.data.buttonSkin;

public class PauseState extends GameState{

    private Stage stage;

    private TextButton button;
    private TextButton button1;
    private Label titleLabel;

    public PauseState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        BitmapFont font = new BitmapFont();
        font.getData().setScale(4);
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        titleLabel = new Label("Game is paused", style);
        titleLabel.setPosition(750, 800);


        button = new TextButton("Resume game", buttonSkin.getSkin());
        button.getLabel().setFontScale(2,2);
        button.setPosition(760,500);
        button.addListener( new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Back to game button clicked!");
                gsm.setState(2);
                return true;
            }
        });

        button1 = new TextButton("Back to main menu", buttonSkin.getSkin());
        button1.getLabel().setFontScale(2,2);
        button1.setPosition(760,350);
        button1.addListener( new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Back to menu button clicked!");
                gsm.setState(0);
                return true;
            }
        });

        stage.addActor(button);
        stage.addActor(button1);
        stage.addActor(titleLabel);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(GameData gameData) {
        stage.draw();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }
}
