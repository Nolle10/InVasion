package main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import data.GameData;
import data.ProcessAt;
import data.World;
import dk.sdu.se.f23.InVasion.enemy.EnemyControlSystem;
import dk.sdu.se.f23.InVasion.enemy.EnemyPlugin;
import managers.GameStateManager;
import services.EntityProcessingService;
import services.PluginService;

import java.util.ArrayList;
import java.util.List;

public class Game implements ApplicationListener {
    public static int WIDTH;
    public static int HEIGHT;

    public static OrthographicCamera cam;

    private GameStateManager gsm;

    //ENEMY TEST
    private final GameData gameData = new GameData();
    private List<EntityProcessingService> entityProcessors = new ArrayList<>();
    private List<PluginService> entityPlugins = new ArrayList<>();
    private World world = new World();

    public void create() {

        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        gameData.setDisplayWidth(WIDTH);
        gameData.setDisplayHeight(HEIGHT);

        cam = new OrthographicCamera(WIDTH, HEIGHT);
        cam.translate(WIDTH / 2, HEIGHT / 2);
        cam.update();

        gsm = new GameStateManager();

        //ENEMY TEST
        PluginService enemyPlugin = new EnemyPlugin();


        EntityProcessingService enemyProcess = new EnemyControlSystem();

        entityPlugins.add(enemyPlugin);
        entityProcessors.add(enemyProcess);
        // Lookup all Game Plugins using ServiceLoader
        for (PluginService iGamePlugin : entityPlugins) {
            iGamePlugin.onEnable(gameData, world);
        }

    }

    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        draw();
        gsm.draw();

        //ENEMY TEST
        gameData.setDelta(Gdx.graphics.getDeltaTime());
        update();

    }

    //ENEMY TEST
    private void update() {
        // Update
        for (EntityProcessingService entityProcessorService : entityProcessors) {
            entityProcessorService.process(gameData, world, ProcessAt.Tick);
        }
    }
    public void draw(){

    }
    public void resize(int width, int height) {}

    public void pause() {}
    public void resume() {}
    public void dispose() {}
}
