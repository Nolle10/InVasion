package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;

public class EndState extends GameState{

    private Stage stage;
    private Label titleLabel;

    public static final int WIN = 1;
    public static final int LOSS = 0;

    public int condition;

    public EndState(GameStateManager gsm, int c) {
        super(gsm);
        System.out.println("c"+c);
        this.condition = c;
        System.out.println("condition"+this.condition);
    }

    @Override
    public void init() {
        stage = new Stage();

        String text = "";
        if (condition == WIN){
            text = "You Won";
            System.out.println("there"+this.condition);
        }
        if (condition == LOSS){
            text = "You Lost";
            System.out.printf("here too"+this.condition);
        }

        BitmapFont font = new BitmapFont();
        font.getData().setScale(4);
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        titleLabel = new Label(text, style);
        titleLabel.setPosition(860, 800);

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
