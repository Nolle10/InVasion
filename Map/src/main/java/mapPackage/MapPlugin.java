package mapPackage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


import java.util.ArrayList;

public class MapPlugin {


    private int height = 1080;
    private int width = 1920;

    private ArrayList<ArrayList<Square>> mapFields;

    public MapPlugin() {
        mapFields = new ArrayList<>();
    }

    public void generateMap() {


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
        DrawMap();
    }

    public void DrawMap() {
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

    public Color getSquareColor(int x, int y) {
        return mapFields.get(x).get(y).getColor();
    }


}
