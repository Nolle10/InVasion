package dk.sdu.se.f23.InVasion.gamestates;

import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.buttonSkin;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.common.events.events.StateChangeEvent;
import dk.sdu.se.f23.InVasion.commonweapon.Weapon;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import dk.sdu.se.f23.InVasion.map.MapPlugin;

public class MainScreenState extends GameState {
    private Stage stage;
    private TextButton startGameButton;
    private TextButton exitGameButton;
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

        startGameButton = new TextButton("Start game", buttonSkin.getSkin());
        startGameButton.getLabel().setFontScale(2, 2);
        startGameButton.setPosition(760, 500);
        startGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateEnum.ShopState);
                EventDistributor.sendEvent(new StateChangeEvent(GameStateEnum.ShopState), gsm.getWorld());
                return true;
            }
        });

        exitGameButton = new TextButton("Exit game", buttonSkin.getSkin());
        exitGameButton.getLabel().setFontScale(2, 2);
        exitGameButton.setPosition(760, 350);
        exitGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });

        // Add actors to the stage
        stage.addActor(titleLabel);
        stage.addActor(startGameButton);
        stage.addActor(exitGameButton);
        gsm.getGameData().addProcessor(stage);
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void draw(GameData gameData) {
        gameData.setPlayerMoney(200);
        gameData.setWaveCount(0);
        stage.draw();


    }

    @Override
    public void handleInput() {
    }

    @Override
    public void dispose() {
        gsm.getGameData().removeProcessor(stage);
    }

    public void emptyWorld() {
        for (Entity entity : gsm.getWorld().getEntities(Weapon.class)) {
            gsm.getWorld().removeEntity(entity);
        }
    }
}
