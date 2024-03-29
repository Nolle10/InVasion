package dk.sdu.se.f23.InVasion.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.common.services.PluginService;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class Game implements ApplicationListener {
    private Collection<? extends EntityProcessingService> entityProcessingServices;
    public static int WIDTH;
    public static int HEIGHT;
    public static OrthographicCamera cam;
    private GameStateManager gsm;
    private final GameData gameData = new GameData();
    private final World world = new World();

    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        gameData.setDisplayWidth(WIDTH);
        gameData.setDisplayHeight(HEIGHT);

        InputMultiplexer inputHandler = new InputMultiplexer();
        gameData.setMultiplexer(inputHandler);
        Gdx.input.setInputProcessor(inputHandler);

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2f, gameData.getDisplayHeight() / 2f);
        cam.update();

        gsm = new GameStateManager(gameData, world);

        for (PluginService plugin : getPluginServices()) {
            plugin.onEnable(gameData, world);
            System.out.println(plugin.getClass().getName() + " loaded");
        }
        gameData.setPlayerMoney(200);
    }

    public void render() {
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.draw();
        gameData.setDelta(Gdx.graphics.getDeltaTime());
        gameData.setSpriteBatch(new SpriteBatch());
        gameData.getSpriteBatch().begin();
        update();
        gameData.getSpriteBatch().end();
    }

    private void update() {
        for (EntityProcessingService entityProcessor : getEntityProcessingServices()) {
            entityProcessor.process(gameData, world);
        }
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }

    //ServiceLoader - Loads in all Plugin services and EntityProcessing services

    private Collection<? extends PluginService> getPluginServices() {
        return ServiceLoader.load(PluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends EntityProcessingService> getEntityProcessingServices() {
        if (entityProcessingServices == null) {
            entityProcessingServices = ServiceLoader.load(EntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
        }
        return entityProcessingServices;
    }
}
