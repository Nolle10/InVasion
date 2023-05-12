package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.buttonSkin;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainScreenState extends GameState{

    private Stage stage;
    private TextButton button;
    private TextButton button1;
    private Label titleLabel;

    public MainScreenState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        stage = new Stage();

        BitmapFont font = new BitmapFont();
        font.getData().setScale(4);
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        titleLabel = new Label("InVasion", style);
        titleLabel.setPosition(860, 800);

        button = new TextButton("Start game", buttonSkin.getSkin());
        button.getLabel().setFontScale(2,2);
        button.setPosition(760,500);
        button.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                gsm.getGameData().removeProcessor(stage);
                gsm.setState(GameStateEnum.ShopState);
                return true;
            }
        });

        button1 = new TextButton("Exit game", buttonSkin.getSkin());
        button1.getLabel().setFontScale(2,2);
        button1.setPosition(760,350);
        button1.addListener( new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                Gdx.app.exit();
                return true;
            }
        });

        // Add actors to the stage
        stage.addActor(titleLabel);
        stage.addActor(button);
        stage.addActor(button1);
        gsm.getGameData().addProcessor(stage);
    }

    @Override
    public void update(float dt) {}

    @Override
    public void draw(GameData gameData) {
        stage.draw();
    }

    @Override
    public void handleInput() {}

    @Override
    public void dispose() {}
}
