package dk.sdu.se.f23.InVasion.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.*;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.enums.GameStateEnum;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.events.BuyTowerEvent;
import dk.sdu.se.f23.InVasion.common.services.PluginService;
import dk.sdu.se.f23.InVasion.common.services.ShopPluginService;
import dk.sdu.se.f23.InVasion.main.Game;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;
import dk.sdu.se.f23.InVasion.map.MapPlugin;

import javax.swing.text.LabelView;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class ShopState extends GameState {

    private ShapeRenderer sr;
    private Stage stage;

    //private ArrayList<ArrayList<Object>> weapons;
    private TextButton button;
    private TextButton button1;
    private TextButton.TextButtonStyle textButtonStyle;
    private TextButton.TextButtonStyle textButtonStyle2;
    private World world;
    private GameData gameData;


    private ImageButton sel;
    private MapPlugin map;

    private int selected = -1;
    private TextButton cost;

    private Label placingLabel ;
    private Label notEnoughMoneyLabel;
    public ShopState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        selected = -1;
        world = gsm.getWorld();
        gameData = gsm.getGameData();
        stage = new Stage();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.BLACK;
        notEnoughMoneyLabel = new Label("Not Enough Money",labelStyle);
        notEnoughMoneyLabel.setVisible(false);
        placingLabel = new Label("Now Placing", labelStyle);
        placingLabel.setVisible(false);

        stage.addActor(placingLabel);
        stage.addActor(notEnoughMoneyLabel);

        sr = new ShapeRenderer();
        map = new MapPlugin();
        map.onEnable(gameData, world);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.WHITE;
        button = new TextButton("Start wave button", textButtonStyle);
        button.setPosition(830, 900);
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.setState(GameStateEnum.PlayState);
                return true;
            }
        });

        textButtonStyle2 = new TextButton.TextButtonStyle();
        textButtonStyle2.font = new BitmapFont();
        textButtonStyle2.fontColor = Color.BLACK;

        button1 = new TextButton(String.format("Current Money: %d", gameData.getPlayerMoney()), textButtonStyle2);
        button1.setPosition(1920-165, 950);
        stage.addActor(button);
        stage.addActor(button1);


        try {
            int iterator = 0;
            for (ShopPluginService shopPlugin : getShopPluginServices()) {
                shopPlugin.onEnable(gameData, world);
                iterator++;
                Texture texture = shopPlugin.getTexture();
                TextureRegionDrawable weaponImage = new TextureRegionDrawable(texture);
                ImageButton but = new ImageButton((weaponImage));
                but.setSize(60,60);
                but.setPosition(1920 - 130, 800 - (iterator * 200));
                but.setName(String.valueOf(iterator));

                but.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        int newSelected = Integer.parseInt(but.getName());
                        if (newSelected == selected) {
                            selected = -1;
                        } else {
                            selected = newSelected;
                        }
                    }
                });

                Label costLabel = new Label("Cost: " + shopPlugin.getCost(), labelStyle);
                costLabel.setPosition(but.getX(), but.getY() - costLabel.getHeight() - 30);
                stage.addActor(costLabel);

                Label nameLabel = new Label(shopPlugin.getWeaponName(), labelStyle);
                nameLabel.setPosition(but.getX(), but.getY() - nameLabel.getHeight() - 5);
                stage.addActor(nameLabel);
                stage.addActor(but);
            }
        } catch (NullPointerException e) {
            System.out.println("There are no weapons goddamn");
        }
        gsm.getGameData().addProcessor(stage);

    }

    @Override
    public void update(float dt) {
        for (ShopPluginService shopPlugin : getShopPluginServices()){
        if (selected == -1) {
            if (sel != null) {
                sel.remove();
                placingLabel.setVisible(false);
                notEnoughMoneyLabel.setVisible(false);
                sel = null;
            }//TODO: Send some kind of event with selected image? Need shop to actually contain WeaponPlugins not just textures
        } else if (shopPlugin.getCost() > gameData.getPlayerMoney()) {
            notEnoughMoneyLabel.setVisible(true);
            notEnoughMoneyLabel.setPosition(1920 - (180), 70);
        } else {
                TextureRegionDrawable weaponImage = new TextureRegionDrawable(shopPlugin.getTexture());
                if (sel == null) {

                    placingLabel.setPosition(1920 - 140, 100);
                    placingLabel.setVisible(true);
                    sel = new ImageButton(weaponImage);
                    sel.setPosition(1920 - 115, 136);

                    stage.addActor(sel);

                } else {
                    sel.getStyle().imageUp = weaponImage;
                }
            }
        }
    }


    @Override
    public void draw(GameData gameData) {

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.LIGHT_GRAY);
        int shopWidth = 200;
        sr.rect(1920 - shopWidth, 0, 200, 1080);
        sr.setColor(Color.DARK_GRAY);
        sr.rect(1920 - shopWidth + 75, 100 + 25, 50, 50);
        sr.end();

        map.draw(gameData);
        stage.draw();

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {
        gameData.removeProcessor(stage);
    }

    private Collection<? extends ShopPluginService> getShopPluginServices() {
        return ServiceLoader.load(ShopPluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
