package dk.sdu.se.f23.InVasion.main;

import bulletpackage.BulletController;
import bulletpackage.BulletPlugin;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.ProcessAt;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.common.services.PluginService;
import dk.sdu.se.f23.InVasion.enemy.EnemyControlSystem;
import dk.sdu.se.f23.InVasion.enemy.EnemyPlugin;
import dk.sdu.se.f23.InVasion.managers.GameStateManager;
import dk.sdu.se.f23.InVasion.player.PlayerControlSystem;
import dk.sdu.se.f23.InVasion.player.PlayerPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class Game implements ApplicationListener {
    public static int WIDTH;
    public static int HEIGHT;

    public static OrthographicCamera cam;

    //Only for demo - remove later
    private List<PluginService> pluginServices = new ArrayList<>();
    private List<EntityProcessingService> entityProcessingServices = new ArrayList<>();

    private GameStateManager gsm;
    private final GameData gameData = new GameData();
    private World world = new World();

    public void create() {

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());
        //WIDTH = Gdx.graphics.getWidth();
        //HEIGHT = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();


        gsm = new GameStateManager();

        //Only for demo - remove later
        //Add your own modules manually for TESTING only
        PluginService enemyPlugin = new EnemyPlugin();
        EntityProcessingService enemyProcess = new EnemyControlSystem();
        pluginServices.add(enemyPlugin);
        entityProcessingServices.add(enemyProcess);
        PluginService playerPlugin = new PlayerPlugin();
        EntityProcessingService playerProcess = new PlayerControlSystem();
        pluginServices.add(playerPlugin);
        entityProcessingServices.add(playerProcess);
        PluginService bullet = new BulletPlugin();
        EntityProcessingService bulletp = new BulletController();
        pluginServices.add(bullet);
        entityProcessingServices.add(bulletp);

        for (PluginService plugin : pluginServices) {
            plugin.onEnable(gameData, world);
        }
        for (PluginService plugin : getPluginServices()) {
            plugin.onEnable(gameData, world);
            System.out.println(plugin.getClass().getName() + " loaded");
        }
    }

    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.draw();
        gameData.setDelta(Gdx.graphics.getDeltaTime());
        update(ProcessAt.Tick);

    }

    //Update method for EntityProcessingServices: How to do it with ProcessAt.Tick and ProcessAt values?
    private void update(ProcessAt processAt){
        for (EntityProcessingService entityProcessor : getEntityProcessingServices()) {
            entityProcessor.process(gameData, world, processAt);
        }
        //For demo - remove later
        for (EntityProcessingService entityProcessor : entityProcessingServices) {
            entityProcessor.process(gameData, world, processAt);
        }
    }

    public void resize(int width, int height) {}

    public void pause() {}
    public void resume() {}
    public void dispose() {}

    //ServiceLoader - Loads in all Plugin services and EntityProcessing services

    private Collection<? extends PluginService> getPluginServices() {
        return ServiceLoader.load(PluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
    private Collection<? extends EntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(EntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
