package dk.sdu.se.f23.InVasion.main;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {

    public static void main(String[] args) {

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("InVasion");
        config.setWindowSizeLimits(1000,600,1920,1080);

        new Lwjgl3Application(new Game(), config);

    }
}
