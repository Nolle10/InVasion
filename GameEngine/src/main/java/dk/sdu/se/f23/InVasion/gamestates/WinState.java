package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.buttonSkin;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;

public class WinState extends GameState {
    private Stage stage;
    private Label titleLabel;
    private TextButton buttonBack;

    public WinState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        stage = new Stage();

        String text = "You Won";
        BitmapFont font = new BitmapFont();
        font.getData().setScale(4);
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        titleLabel = new Label(text, style);
        titleLabel.setPosition(860, 800);

        buttonBack = new TextButton("Back to Main Menu", buttonSkin.getSkin());
        buttonBack.getLabel().setFontScale(2, 2);
        buttonBack.setPosition(760, 400);
        buttonBack.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateEnum.MainScreen);
                return true;
            }
        });

        stage.addActor(titleLabel);
        stage.addActor(buttonBack);
        gsm.getGameData().addProcessor(stage);
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
        gsm.getGameData().removeProcessor(stage);
    }
}
