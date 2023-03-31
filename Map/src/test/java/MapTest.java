import com.badlogic.gdx.Game;
import junit.framework.Assert;
import mapPackage.MapPlugin;
import org.junit.Test;
import org.testng.reporters.jq.Main;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapTest {
    @Test
    public void checkMaskSize(){

        MapPlugin mapPlugin = new MapPlugin();
        ArrayList<ArrayList<Integer>> mask = mapPlugin.generateMask();
        assertEquals(mapPlugin.getWidth(), mask.size());
        assertEquals(mapPlugin.getHeight(), mask.get(0).size());
    }

}