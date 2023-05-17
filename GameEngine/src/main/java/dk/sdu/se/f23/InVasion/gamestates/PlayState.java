package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;
import dk.sdu.se.f23.InVasion.map.MapPlugin;

import java.util.Map;

public class PlayState extends GameState {


    private Stage stage;

    private TextButton button;
    private TextButton button1;
    private Label labelWave;
    private TextButton.TextButtonStyle textButtonStyle;

    private MapPlugin map;

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        stage = new Stage();
        map = new MapPlugin();


        map.onEnable(gsm.getGameData(), gsm.getWorld());
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.YELLOW;
        button = new TextButton("go back to shop button", textButtonStyle);
        button.setPosition(900,800);
        button.addListener( new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                gsm.getGameData().removeProcessor(stage);
                gsm.setState(GameStateEnum.ShopState);
                return true;
            }
        });
        button1 = new TextButton("pause button", textButtonStyle);
        button1.setPosition(1750,900);
        button1.addListener( new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                gsm.getGameData().removeProcessor(stage);
                gsm.setState(GameStateEnum.PauseState);
                return true;
            }
        });

        BitmapFont font = new BitmapFont();
        font.getData().setScale(3);
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        labelWave = new Label(String.format("Wave: %d", gsm.getGameData().getWaveCount()), style);
        labelWave.setPosition(100, 950);

        stage.addActor(button);
        stage.addActor(button1);
        stage.addActor(labelWave);
        gsm.getGameData().addProcessor(stage);

    }

    public void update(float dt) {
        handleInput();
    }

    public void draw(GameData gameData) {
        stage.draw();
        map.draw(gameData);

    }

    public void handleInput() {

    }

    public void dispose() {
        gsm.getGameData().removeProcessor(stage);
        map.clearStuff(gsm.getGameData(), gsm.getWorld());
    }
}