package main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import data.Entity;
import data.GameData;
import data.World;
import managers.GameStateManager;
import services.EntityProcessingService;
import services.PluginService;
import util.SPILocator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game implements ApplicationListener {
    public static int WIDTH;
    public static int HEIGHT;

    public static OrthographicCamera cam;
    private ShapeRenderer sr;

    private GameStateManager gsm;

    private final GameData gameData = new GameData();
    private World world = new World();
    private List<EntityProcessingService> entityProcessors = new ArrayList<>();

    public void create() {

        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(WIDTH, HEIGHT);
        cam.translate(WIDTH / 2, HEIGHT / 2);
        cam.update();


        gsm = new GameStateManager();

        for(PluginService gamePlugin : getPluginServices()){
            gamePlugin.onEnable(gameData, world);
        }

    }

    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();

    }

        private void update() {
            // Update
            for (EntityProcessingService entityProcessorService : getEntityProcessingServices()) {
                entityProcessorService.process(gameData, world);
            }
        }

        private void draw() {
            for (Entity entity : world.getEntities()) {

                sr.setColor(255, 255, 255, 1);

                sr.begin(ShapeRenderer.ShapeType.Line);

                float[] shapex = entity.getShapeX();
                float[] shapey = entity.getShapeY();

                for (int i = 0, j = shapex.length - 1;
                     i < shapex.length;
                     j = i++) {

                    sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
                }

                sr.end();
            }
        }

    public void resize(int width, int height) {}

    public void pause() {}
    public void resume() {}
    public void dispose() {}

    private Collection<? extends PluginService> getPluginServices() {
        return SPILocator.locateAll(PluginService.class);
    }

    private Collection<? extends EntityProcessingService> getEntityProcessingServices() {
        return SPILocator.locateAll(EntityProcessingService.class);
    }
}
