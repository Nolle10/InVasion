package mappackage;

import com.badlogic.gdx.graphics.Color;
import dk.sdu.se.f23.InVasion.common.data.Entity;

import java.util.ArrayList;

public class Square {


    private ArrayList<Entity> entitiesInSquare;
    private boolean isOccupied;

    private Color color;
    private int size = 1;

    public Square(){

        entitiesInSquare = new ArrayList<>();
        isOccupied = false;
        color = Color.BLACK;

    }
    public Square(Color color){

        entitiesInSquare = new ArrayList<>();
        isOccupied = false;
        this.color = color;
    }

    public void setColor(Color c){
      color = c;
    }
    public Color getColor(){
        return color;
    }

}
