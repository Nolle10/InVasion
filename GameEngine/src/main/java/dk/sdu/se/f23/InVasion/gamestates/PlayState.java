package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.MouseProcessor;
import dk.sdu.se.f23.InVasion.common.data.buttonSkin;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;
import dk.sdu.se.f23.InVasion.map.MapPlugin;

public class PlayState extends GameState {
    private Stage stage;
    private MapPlugin map;
    private Label baseHealthLabel;

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        stage = new Stage();
        map = new MapPlugin(gsm.getWorld());
        map.onEnable(gsm.getGameData(), gsm.getWorld());

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.WHITE;

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.WHITE;
        TextButton backToShopButton = new TextButton("Go to Shop", buttonSkin.getSkin());
        backToShopButton.setSize(180, 60);
        backToShopButton.setPosition(1750, 800);
        backToShopButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateEnum.ShopState);
                return true;
            }
        });
        TextButton pauseButton = new TextButton("Pause", buttonSkin.getSkin());
        pauseButton.setSize(160, 60);
        pauseButton.setPosition(1750, 900);
        pauseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateEnum.PauseState);
                return true;
            }
        });

        BitmapFont font = new BitmapFont();
        font.getData().setScale(3);
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        Label labelWave = new Label(String.format("Wave: %d", gsm.getGameData().getWaveCount()), style);
        labelWave.setPosition(100, 950);

        stage.addActor(backToShopButton);
        stage.addActor(pauseButton);
        stage.addActor(labelWave);
        gsm.getGameData().addProcessor(stage);
        gsm.getGameData().addProcessor(MouseProcessor.getInstance());

        baseHealthLabel = new Label("Base health: " + gsm.getWorld().getBaseHealth(), labelStyle);
        baseHealthLabel.setPosition(1760, 990);
        stage.addActor(baseHealthLabel);
    }

    public void update(float dt) {
        if (gsm.getWorld().getBaseHealth() <= 0) {
            gsm.setState(GameStateEnum.LossState);
        }
        baseHealthLabel.setText("Base health: " + gsm.getWorld().getBaseHealth());

        handleInput();
    }

    public void draw(GameData gameData) {
        map.draw(gameData);
        stage.draw();
    }

    public void handleInput() {
    }

    public void dispose() {
        gsm.getGameData().removeProcessor(stage);
        map.clearStuff(gsm.getGameData());
    }
}