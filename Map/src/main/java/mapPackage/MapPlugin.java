package mapPackage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import data.GameData;
import data.Point;
import data.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MapPlugin {


    private int height = 1080;
    private int width = 1920;

    private ArrayList<ArrayList<Square>> mapFields;

    public MapPlugin() {
        mapFields = new ArrayList<>();
    }


    public void onEnable(GameData gameData, World world){
        world.loadWorldMask(generateMask());
        world.setInitState(new Point(0,0));
        world.setGoalState(new Point(width,height));
        for (int i = 0; i < height; i++) {
            ArrayList<Square> lines = new ArrayList<>();

            for (int j = 0; j < width; j++) {
                switch ((j + i) % 10) {
                    case 0:
                        lines.add(new Square(Color.BLACK));
                        break;
                    case 1:
                        lines.add(new Square(Color.BLUE));
                        break;
                    case 2:
                        lines.add(new Square(Color.PINK));
                        break;
                    case 3:
                        lines.add(new Square(Color.CHARTREUSE));
                        break;
                    case 4:
                        lines.add(new Square(Color.OLIVE));
                        break;
                    case 5:
                        lines.add(new Square(Color.GREEN));
                        break;
                    case 6:
                        lines.add(new Square(Color.FIREBRICK));
                        break;
                    default:
                        lines.add(new Square(Color.GRAY));
                        break;
                }
            }
            mapFields.add(lines);
        }
    }

    public ArrayList<ArrayList<Integer>> generateMask(){
        ArrayList<ArrayList<Integer>> mask = new ArrayList<>();
        BufferedImage maskImage = null;
        try {
             maskImage = ImageIO.read(new File("src/main/java/mapPackage/mask.png"));
        } catch (IOException e) {
            System.out.println("Picture not found");
            throw new NoSuchElementException(e);
        }
        for (int i = 0; i < width; i++) {
            ArrayList<Integer> line = new ArrayList<>();
            for(int j = 0; j<height;j++){
                int rgb = maskImage.getRGB(i,j);
                switch (rgb){
                    case -1: //White
                        line.add(0);
                        break;
                    case 0: // Black
                        line.add(1);
                        break;
                    case 125: // Unknown color please provide right one
                        line.add(2);
                        break;
                    default:
                        System.out.println("I do not know this pixel");
                        throw new NoSuchElementException();
                }
            }
            mask.add(line);
        }
        return  mask;
    }
    public void onDisable(GameData gameData, World world){
        mapFields.clear();
    }

public int getHeight(){
        return height;
}
public void  setHeight(int h){
        height = h;
}

public int getWidth(){
        return width;
}
public void setWidth(int w){
        width = w;
}
    public void draw() {
        ShapeRenderer shape = new ShapeRenderer();
        shape.begin(ShapeRenderer.ShapeType.Filled);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                shape.setColor(getSquareColor(i, j));
                shape.box(j, i, 0, 1, 1, 0);
            }
        }
        shape.end();
    }
    private Color getSquareColor(int x, int y) {
        return mapFields.get(x).get(y).getColor();
    }