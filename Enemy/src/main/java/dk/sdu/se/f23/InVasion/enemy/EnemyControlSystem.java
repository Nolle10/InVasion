package dk.sdu.se.f23.InVasion.enemy;


import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.ProcessAt;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MovingPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;
import dk.sdu.se.f23.InVasion.enemy.services.ActionService;


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
