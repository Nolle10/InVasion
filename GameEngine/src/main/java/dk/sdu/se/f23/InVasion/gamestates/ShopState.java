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
        labelStyle.fontColor = Color.RED;
        notEnoughMoneyLabel = new Label("Not Enough Money",labelStyle);
        labelStyle.fontColor = Color.BLACK;
        placingLabel = new Label("Now Placing", labelStyle);

        stage.addActor(placingLabel);
        stage.addActor(notEnoughMoneyLabel);
        sr = new ShapeRenderer();
        map = new MapPlugin();
        map.onEnable(gameData, world);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.RED;
        button = new TextButton("Start wave button", textButtonStyle);
        button.setPosition(900, 900);
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.getGameData().removeProcessor(stage);
                gsm.setState(GameStateEnum.PlayState);
                return true;
            }
        });

        button1 = new TextButton(String.format("Current Money: %d", gameData.getPlayerMoney()), textButtonStyle);
        button1.setPosition(1700, 100);
        stage.addActor(button);
        stage.addActor(button1);


        int shopWidth = 200;
        try {
            int iterator = 0;
            for (ShopPluginService shopPlugin : getShopPluginServices()) {
                shopPlugin.onEnable(gameData, world);
                iterator++;
                Texture texture = shopPlugin.getTexture();
                TextureRegionDrawable weaponImage = new TextureRegionDrawable(texture);
                ImageButton but = new ImageButton((weaponImage));
                but.setPosition(1920 - (shopWidth), 800 - (iterator * 200));
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
                costLabel.setPosition(but.getX() + 20, but.getY() - costLabel.getHeight() - 10);
                stage.addActor(costLabel);

                Label nameLabel = new Label(shopPlugin.getWeaponName(), labelStyle);
                nameLabel.setPosition(but.getX() + 30, but.getY() - nameLabel.getHeight() + 10);
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
            notEnoughMoneyLabel.setPosition(1920 - (200), 80);
        } else {
                TextureRegionDrawable weaponImage = new TextureRegionDrawable(shopPlugin.getTexture());
                if (sel == null) {

                    placingLabel.setPosition(1920 - (200), 80);
                    placingLabel.setVisible(true);
                    sel = new ImageButton(weaponImage);
                    textButtonStyle.fontColor = Color.RED;

                    sel.setPosition(1920 - (200), 100);

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
        sr.setColor(Color.YELLOW);
        int shopWidth = 200;
        sr.rect(1920 - shopWidth, 0, 200, 1080);
        sr.setColor(Color.DARK_GRAY);
        sr.rect(1920 - (200) + 55, 100 + 25, 50, 50);
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
