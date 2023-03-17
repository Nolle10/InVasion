package main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {

    public static void main(String[] args) {

        LwjglApplicationConfiguration cfg =
                new LwjglApplicationConfiguration();
        cfg.title = "InVasion";
        cfg.width = 1920;
        cfg.height = 1080;
        cfg.useGL30 = false;
        cfg.resizable = false;


        new LwjglApplication(new Game(), cfg);

    }
}
