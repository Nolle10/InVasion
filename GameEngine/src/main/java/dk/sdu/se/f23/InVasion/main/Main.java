package dk.sdu.se.f23.InVasion.main;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {

    public static void main(String[] args) {

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("InVasion");
        int height = 1080;
        int width = 1920;
        config.setWindowSizeLimits(width,height,width,height);
        config.setWindowedMode(width,height);

        new Lwjgl3Application(new Game(), config);

    }
}
