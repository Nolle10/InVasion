package dk.sdu.se.f23.InVasion.map;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.services.PluginService;

import javax.imageio.ImageIO;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


public class MapPlugin implements PluginService {


    private int height = 720;
    private int width = 1280;
    private int tilesSize = 24;
    private ArrayList<ArrayList<Square>> mapFields;
    private ArrayList<Texture> tiles;
    private  ArrayList<ArrayList<Integer>> mask;

    public MapPlugin() {
        mapFields = new ArrayList<>();
        tiles = new ArrayList<>();
        Texture texture1 = new Texture(Gdx.files.internal("Map/src/main/resources/dk/sdu/se/f23/InVasion/mapresources/textures/pixil-frame-1.png"));
        Texture texture2 = new Texture(Gdx.files.internal("Map/src/main/resources/dk/sdu/se/f23/InVasion/mapresources/textures/pixil-frame-0.png"));
        Texture texture3 = new Texture(Gdx.files.internal("Map/src/main/resources/dk/sdu/se/f23/InVasion/mapresources/textures/pixil-frame-3.png"));
        tiles.add(texture1);
        tiles.add(texture3);
        tiles.add(texture2);
    }


    public void onEnable(GameData gameData, World world) {
        world.loadWorldMask(generateMask());
        world.setInitState(new Point(0, 0));
        world.setGoalState(new Point(width, height));



       //draw();
    }

    public ArrayList<ArrayList<Integer>> generateMask() {
        ArrayList<ArrayList<Integer>> mask = new ArrayList<>();
        BufferedImage maskImage = null;
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
                int rgb = maskImage.getRGB(i, j);
                switch (rgb) {
                    case -1 -> //White
                            line.add(0);
                    case -16777216 -> // Black
                            line.add(1);
                    case -8421505 -> // Unknown color please provide right one
                            line.add(2);
                    default -> {
                        System.out.println("I do not know this pixel: " + rgb);
                        throw new NoSuchElementException();
                    }
                }
            }
            mask.add(line);
        }
        this.mask = mask;
        return mask;
    }

    public void onDisable(GameData gameData, World world) {
        mapFields.clear();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int h) {
        height = h;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int w) {
        width = w;
    }

    public void draw(GameData gameData) {
        System.out.println("HELLO");
        generateMask();
        HashMap<Integer,Integer> occurenceMap= new HashMap<>();
        gameData.getSpriteBatch().begin();
        for (int i = 0; i < height; i++) {
            ArrayList<Square> lines = new ArrayList<>();
          //  System.out.println("IM SAD");
            for (int j = 0; j < width; j++) {
                int switcher = mask.get(j).get(i);
                switch (switcher) {
                    case 0 -> {
                        Square s = new Square(Color.RED);
                        s.setIsOccupied(false);
                        lines.add(s);
                    }
                    // System.out.println("White");
                    case 1 -> {
                        Square square = new Square(Color.SCARLET);
                        square.setIsOccupied(true);
                        // System.out.println("Black");
                        lines.add(square);
                    }
                    case 2 -> {
                        Square sq = new Square(Color.SALMON);
                        sq.setIsOccupied(true);
                        //  System.out.println("Grey");
                        lines.add(sq);
                    }
                    default -> {
                        System.out.println("SOMETHING WENT WRONG ");
                        throw new NoSuchElementException();
                    }
                }
              //  System.out.println(height+" :  "+width);
                if(i%tilesSize == 0 && j%tilesSize==0){
                    int max = 0;
                    System.out.println("DRAW BITCH");
                    int maxId = 0;
                    for(Map.Entry<Integer,Integer> numberId : occurenceMap.entrySet()){
                        if( numberId.getValue()>max){
                            max = numberId.getValue();
                            maxId = numberId.getKey();
                        }
                    }

                    gameData.getSpriteBatch().draw(tiles.get(maxId),j,i,tilesSize,tilesSize);

                }
                else {
                    Integer key = mask.get(j).get(i);
                    if (occurenceMap.containsKey(key)){
                        occurenceMap.put(key,occurenceMap.get(key)+1);
                    }
                    else {
                        occurenceMap.put(key,1);
                    }
                }


            }

            mapFields.add(lines);


        }
        gameData.getSpriteBatch().end();

    }

    private Color getSquareColor(int x, int y) {
        return mapFields.get(x).get(y).getColor();
    }
}