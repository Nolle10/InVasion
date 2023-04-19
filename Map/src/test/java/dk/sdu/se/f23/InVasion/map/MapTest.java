package dk.sdu.se.f23.InVasion.map;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {
    @Test
    public void checkMaskSize(){

        MapPlugin mapPlugin = new MapPlugin();
        ArrayList<ArrayList<Integer>> mask = mapPlugin.generateMask();
        assertEquals(mapPlugin.getWidth(), mask.size());
        assertEquals(mapPlugin.getHeight(), mask.get(0).size());
    }

}