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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.events.BuyTowerEvent;
import dk.sdu.se.f23.InVasion.common.services.PluginService;
import dk.sdu.se.f23.InVasion.main.Game;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;
import dk.sdu.se.f23.InVasion.map.MapPlugin;

import java.util.ArrayList;
import java.util.Objects;

public class ShopState extends GameState {

    private ShapeRenderer sr;
    private Stage stage;

    private ArrayList<ArrayList<Object>> weapons;
    private TextButton button;
    private TextButton button1;
    private TextButton.TextButtonStyle textButtonStyle;
    private World world ;
    private GameData gameData;
    private ArrayList<Actor> staticActors;

    private ImageButton sel;
    private MapPlugin map;

    private  int selected =-1;
    public ShopState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        selected = -1;
        weapons = new ArrayList<>();
        weapons.addAll(gsm.getWorld().getWeapons());
        weapons.addAll(gsm.getWorld().getWeapons());
        world = gsm.getWorld();
        gameData = gsm.getGameData();
        stage = new Stage();

        sr = new ShapeRenderer();
         map = new MapPlugin();
         map.onEnable(gameData,world);
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

        button1 = new TextButton(String.format("Current Money: %o",gameData.getPlayerMoney()),textButtonStyle);
        button1.setPosition(700, 800);
        stage.addActor(button);
        stage.addActor(button1);
        gsm.getGameData().addProcessor(stage);
        staticActors = new ArrayList<>();
        for(Actor actor: stage.getActors()){
            staticActors.add(actor);
        }

        int shopWidth= 200;
        try {
            for(int i = 0; i< weapons.size();i++) {
                Texture t = (Texture) weapons.get(i).get(1);
                TextureRegionDrawable weaponImage = new TextureRegionDrawable(t);
                ImageButton but = new ImageButton((weaponImage));
                but.setPosition( 1920 - (shopWidth), 800-(i*200));
                but.setName(String.valueOf(i));
                but.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        //  EventDistributor.sendEvent(new BuyTowerEvent(new Point()),world);
                        int newSelected = Integer.parseInt(but.getName());
                        if (newSelected == selected){selected = -1;}
                        else {selected = newSelected;}
                        System.out.println("CLICKED");
                    }
                });
                //  spriteBatch.draw(t, 1920 - (shopWidth), 800-(i*200));
                stage.addActor(but);
            }}
        catch (NullPointerException e){
            System.out.println("There are no weapons goddamn");
        }

    }
    public void redrawShopBackground(){

    }
    @Override
    public void update(float dt) {
        if (selected == -1) {
            if (sel != null) {
                sel.remove();
                sel = null;
            }
        } else {
            Texture t = (Texture) weapons.get(selected).get(1);
            TextureRegionDrawable weaponImage = new TextureRegionDrawable(t);

            if (sel == null) {

                sel = new ImageButton((weaponImage));

                sel.setPosition(1920 - (200), 100);
                stage.addActor(sel);
            } else {
                sel.getStyle().imageUp = weaponImage;
            }
        }
    }



    @Override
    public void draw(GameData gameData) {

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.YELLOW);
        int shopWidth= 200;
        sr.rect(1920-shopWidth,0,200,1080);
        sr.end();

        map.draw(gameData);



    //    SpriteBatch spriteBatch = gameData.getSpriteBatch();
       // spriteBatch.begin();


        //System.out.println(selected);
      //  spriteBatch.end();
      //  stage.draw();
        stage.draw();

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }
}
