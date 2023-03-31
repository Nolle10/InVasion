package dk.sdu.se.f23.InVasion.enemy;

import data.Entity;
import data.GameData;
import data.ProcessAt;
import data.World;
import data.entityparts.LifePart;
import data.entityparts.MovingPart;
import data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.enemy.services.ActionService;
import services.EntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements EntityProcessingService {

    private Entity enemy = new Enemy();
    private final GameData gameData = new GameData();
    private World world = new World();


    public EnemyControlSystem() {
    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            LifePart lifePart = enemy.getPart(LifePart.class);
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);

            //set up movement (left, right, up etc.)
            //TODO: Do movement stuff

            lifePart.process(data, enemy);
            positionPart.process(data, enemy);
            movingPart.process(data, enemy);
        }
    }

    //TODO: Correct this to not just calculate it - save it somewhere?
    private void Update(){
        for (ActionService actionService : getActionServices()) {
            actionService.calculate(gameData, world, (Enemy) enemy);
        }
    }

    private Collection<? extends ActionService> getActionServices() {
        return ServiceLoader.load(ActionService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


//TODO: Delete this
    @Deprecated
    @Override
    public void process(GameData data, World world) {}
}
