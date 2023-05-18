package dk.sdu.se.f23.InVasion.map;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.shop.Buyable;
import dk.sdu.se.f23.InVasion.common.events.EventDistributor;
import dk.sdu.se.f23.InVasion.common.events.events.BuyTowerEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class MapPlugin{


    private final int height = 1080;
    private final int width = 1720;
    private final int tilesSize = 36;
    private final ArrayList<Texture> tiles;
    private ArrayList<ArrayList<Integer>> mask;
    private Stage stage;
    private World world;
    private boolean isClicked = false;
    private Actor clickedField = null;
    private Buyable selectedShopItem = null;

    public MapPlugin() {
        stage = new Stage();
        tiles = new ArrayList<>();
        addTextures();
    }

    private void addTextures() {
        tiles.add(new Texture(Gdx.files.internal("Map/src/main/resources/dk/sdu/se/f23/InVasion/mapresources/textures/pixil-frame-1.png")));
        tiles.add(new Texture(Gdx.files.internal("Map/src/main/resources/dk/sdu/se/f23/InVasion/mapresources/textures/pixil-frame-3.png")));
        tiles.add(new Texture(Gdx.files.internal("Map/src/main/resources/dk/sdu/se/f23/InVasion/mapresources/textures/pixil-frame-0.png")));
    }


    public void onEnable(GameData gameData, World world) {
        world.loadWorldMask(generateMask());
        world.setInitState(new Point(0, 0));
        world.setGoalState(new Point(width, height));
        mask = generateMask();
        this.world = world;
        generateClickableMap();
    }

    public ArrayList<ArrayList<Integer>> generateMask() {
        ArrayList<ArrayList<Integer>> mask = new ArrayList<>();
        BufferedImage maskImage;
        try {
            InputStream maskImageStream = getClass().getResourceAsStream("/dk/sdu/se/f23/InVasion/mapresources/textures/mask.png");
            assert maskImageStream != null;
            maskImage = ImageIO.read(maskImageStream);
        } catch (IOException e) {
            System.out.println("Picture not found");
            throw new NoSuchElementException(e);
        }
        for (int i = 0; i < width; i++) {
            ArrayList<Integer> line = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                switch (maskImage.getRGB(i, j)) {
                    case -1 -> //White
                            line.add(0);
                    case -16777216 -> // Black
                            line.add(1);
                    case -8421505 -> // Unknown color please provide right one
                            line.add(2);
                    default -> {
                        System.out.println("An unexpected color was found: " + maskImage.getRGB(i, j));
                        throw new NoSuchElementException();
                    }
                }
            }
            mask.add(line);
        }
        this.mask = mask;
        return mask;
    }

    public void clearStuff(GameData gameData) {
        gameData.removeAllProccessors();
        stage.clear();
    }

    public void setSelected(Buyable selected) {
        this.selectedShopItem = selected;
    }

    public void generateClickableMap() {
        HashMap<Integer, Integer> occurrenceMap = new HashMap<>();
        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {

                if (i % tilesSize == 0 && j % tilesSize == 0) {
                    Optional<Map.Entry<Integer, Integer>> maxEntry = occurrenceMap.entrySet()
                            .stream()
                            .max(Map.Entry.comparingByValue());

                    int maxId = maxEntry.map(Map.Entry::getKey).orElse(0);

                    Texture texture = tiles.get(maxId);
                    TextureRegionDrawable weaponImage = new TextureRegionDrawable(texture);
                    ImageButton but = new ImageButton((weaponImage));
                    but.setSize(tilesSize, tilesSize);
                    but.setPosition(j, i);

                    if (maxId == 2) {
                        but.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                isClicked = true;
                                clickedField = event.getListenerActor();
                            }
                        });
                    }
                    stage.addActor(but);
                    occurrenceMap.clear();
                } else {
                    Integer key = mask.get(j).get(i);
                    if (occurrenceMap.containsKey(key)) {
                        occurrenceMap.put(key, occurrenceMap.get(key) + 1);
                    } else {
                        occurrenceMap.put(key, 1);
                    }
                }
            }

        }
    }

    public void draw(GameData gameData) {
        if (isClicked && selectedShopItem != null) {
            EventDistributor.sendEvent(new BuyTowerEvent(new Point((int) clickedField.getX(), (int) clickedField.getY()), selectedShopItem.getName()), world);
            gameData.setPlayerMoney(gameData.getPlayerMoney() - selectedShopItem.getPrice());
        }
        isClicked = false;
        stage.draw();
        gameData.removeProcessor(stage);
        gameData.addProcessor(stage);

    }
}