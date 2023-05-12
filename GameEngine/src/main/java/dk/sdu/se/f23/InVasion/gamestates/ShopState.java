package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.main.Game;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;

import java.util.ArrayList;
import java.util.Objects;

public class ShopState extends GameState {

    private ShapeRenderer sr;
    private Stage stage;

    private ArrayList<ArrayList<Object>> weapons;
    private TextButton button;
    private TextButton button1;
    private TextButton.TextButtonStyle textButtonStyle;

    public ShopState(GameStateManager gsm) {
        super(gsm);
        weapons = new ArrayList<>();
        weapons.addAll(gsm.getWorld().getWeapons());
    }

    @Override
    public void init() {
        stage = new Stage();

        sr = new ShapeRenderer();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.RED;
        button = new TextButton("Start wave button", textButtonStyle);
        button.setPosition(900,900);
        button.addListener( new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                gsm.getGameData().removeProcessor(stage);
                gsm.setState(2);
                return true;
            }
        });

        button1 = new TextButton(String.format("Current Money: %o", new GameData().getPlayerMoney()),textButtonStyle);
        button1.setPosition(700, 800);
        stage.addActor(button);
        stage.addActor(button1);
        gsm.getGameData().addProcessor(stage);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(GameData gameData) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.YELLOW);
        int shopWidth= 200;
        sr.rect(1920-shopWidth,0,200,1080);
        sr.end();

        SpriteBatch spriteBatch = gameData.getSpriteBatch();
        spriteBatch.begin();
        try {
        for(int i = 0; i< weapons.size();i++) {
            Texture t = (Texture) weapons.get(i).get(1);
            spriteBatch.draw(t, 1920 - (shopWidth), 800-(i*200));

        }}
        catch (NullPointerException e){
            System.out.println("There are no weapons goddamn");
        }
        spriteBatch.end();
        stage.draw();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }
}
