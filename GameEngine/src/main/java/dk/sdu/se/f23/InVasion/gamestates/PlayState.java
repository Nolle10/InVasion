package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;

public class PlayState extends GameState {

    private ShapeRenderer sr;
    private Stage stage;
    /*
    private TextButton button;
    private TextButton button1;
    private TextButton.TextButtonStyle textButtonStyle;*/

    private GameData gameData = new GameData();
    private World world = new World();

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        /*
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.YELLOW;
        button = new TextButton("go back to shop button", textButtonStyle);
        button.setPosition(900,800);
        button.addListener( new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Shop button clicked!");
                gsm.setState(1);
                return true;
            }
        });
        button1 = new TextButton("pause button", textButtonStyle);
        button1.setPosition(1500,900);
        button1.addListener( new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Pause button clicked!");
                gsm.setState(3);
                return true;
            }
        });
        stage.addActor(button);
        stage.addActor(button1);*/



        sr = new ShapeRenderer();
    }

    public void update(float dt) {

        handleInput();

    }

    public void draw() {

        stage.draw();
    }

    public void handleInput() {
    }

    public void dispose() {
    }
}