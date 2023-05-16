package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;
import dk.sdu.se.f23.InVasion.map.MapPlugin;

import java.util.Map;

public class PlayState extends GameState {


    private Stage stage;

    private TextButton button;
    private TextButton button1;
    private TextButton.TextButtonStyle textButtonStyle;


    private World world = new World();
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
                gsm.setState(1);
                return true;
            }
        });
        button1 = new TextButton("pause button", textButtonStyle);
        button1.setPosition(1750,900);
        button1.addListener( new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                gsm.getGameData().removeProcessor(stage);
                gsm.setState(3);
                return true;
            }
        });
        stage.addActor(button);
        stage.addActor(button1);

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