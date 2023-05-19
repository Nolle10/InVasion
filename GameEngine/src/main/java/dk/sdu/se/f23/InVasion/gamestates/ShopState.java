package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.buttonSkin;
import dk.sdu.se.f23.InVasion.common.data.shop.Buyable;
import dk.sdu.se.f23.InVasion.common.data.shop.BuyableManager;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;
import dk.sdu.se.f23.InVasion.map.MapPlugin;

public class ShopState extends GameState {

    private ShapeRenderer sr;
    private Stage stage;

    //private ArrayList<ArrayList<Object>> weapons;
    private TextButton startWave;
    private TextButton playerMoney;
    private TextButton.TextButtonStyle textButtonStyle;
    private TextButton.TextButtonStyle textButtonStyle2;
    private World world;
    private GameData gameData;


    private ImageButton sel;
    private MapPlugin map;

    private String selected = null;

    private Label placingLabel;
    private Label notEnoughMoneyLabel;

    public ShopState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        world = gsm.getWorld();
        gameData = gsm.getGameData();
        stage = new Stage();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.BLACK;
        notEnoughMoneyLabel = new Label("Not Enough Money", labelStyle);
        notEnoughMoneyLabel.setVisible(false);
        placingLabel = new Label("Now Placing", labelStyle);
        placingLabel.setVisible(false);

        stage.addActor(placingLabel);
        stage.addActor(notEnoughMoneyLabel);

        sr = new ShapeRenderer();
        map = new MapPlugin(gsm.getWorld());
        map.onEnable(gameData, world);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.WHITE;
        startWave = new TextButton("Start wave", buttonSkin.getSkin());
        startWave.setSize(160, 60);
        startWave.setPosition(1920-175, 990);
        startWave.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateEnum.PlayState);
                return true;
            }
        });

        textButtonStyle2 = new TextButton.TextButtonStyle();
        textButtonStyle2.font = new BitmapFont();
        textButtonStyle2.fontColor = Color.BLACK;

        playerMoney = new TextButton(String.format("Current Money: %d", gameData.getPlayerMoney()), textButtonStyle2);
        playerMoney.setPosition(1920 - 160, 950);
        stage.addActor(startWave);
        stage.addActor(playerMoney);

        int iterator = 0;
        for (Buyable buyable : BuyableManager.getAllBuyables()) {
            iterator++;
            Texture texture = buyable.getTexture();
            TextureRegionDrawable weaponImage = new TextureRegionDrawable(texture);
            ImageButton but = new ImageButton((weaponImage));
            but.setSize(60, 60);
            but.setPosition(1920 - 130, 1000 - (iterator * 200));
            but.setName(buyable.getName());

            but.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (buyable.getName().equals(selected)) {
                        selected = null;
                    } else {
                        selected = buyable.getName();
                    }
                }
            });

            Label costLabel = new Label("Cost: " + buyable.getPrice(), labelStyle);
            costLabel.setPosition(but.getX(), but.getY() - costLabel.getHeight() - 30);
            stage.addActor(costLabel);

            Label nameLabel = new Label(buyable.getName(), labelStyle);
            nameLabel.setPosition(but.getX(), but.getY() - nameLabel.getHeight() - 5);
            stage.addActor(nameLabel);
            stage.addActor(but);
        }

        gsm.getGameData().addProcessor(stage);

    }

    @Override
    public void update(float dt) {
        for (Buyable buyable : BuyableManager.getAllBuyables()) {
            if (selected == null) {
                if (sel != null) {
                    sel.remove();
                    placingLabel.setVisible(false);
                    notEnoughMoneyLabel.setVisible(false);
                    sel = null;
                }
                return;
            } else if (!buyable.getName().equals(selected)) {
                continue;
            }


            if (buyable.getPrice() > gameData.getPlayerMoney()) {
                notEnoughMoneyLabel.setVisible(true);
                notEnoughMoneyLabel.setPosition(1920 - 140, 70);
                selected = null;
                map.setSelected(null);
                return;
            }

            TextureRegionDrawable weaponImage = new TextureRegionDrawable(buyable.getTexture());
            if (sel == null) {

                placingLabel.setPosition(1920 - 140, 100);
                placingLabel.setVisible(true);
                sel = new ImageButton(weaponImage);
                sel.setPosition(1920 - 110, 136);
                stage.addActor(sel);

            } else {
                sel.getStyle().imageUp = weaponImage;
            }
            map.setSelected(buyable);

        }
    }


    @Override
    public void draw(GameData gameData) {

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.LIGHT_GRAY);
        int shopWidth = 200;
        sr.rect(1920 - shopWidth, 0, 200, 1080);
        sr.setColor(Color.DARK_GRAY);
        sr.rect(1920 + 77 - shopWidth, 100 + 25, 50, 50);
        sr.end();

        map.draw(gameData);
        playerMoney.setText(String.format("Current Money: %d", gameData.getPlayerMoney()));
        stage.draw();

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {
        gameData.removeProcessor(stage);
    }
}
