package dk.sdu.se.f23.InVasion.common.data.shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuyableManager {
    private static List<Buyable> shoppingList = new ArrayList<>();

    public static void addBuyable(Buyable buyable){
        shoppingList.add(buyable);
    }

    public static void removeBuyable(Buyable buyable){
        shoppingList.remove(buyable);
    }

    public static List<Buyable> getAllBuyables(){
        return Collections.unmodifiableList(shoppingList);
    }



}
