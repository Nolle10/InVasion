package dk.sdu.se.f23.InVasion.common.data;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class World {
    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    private ArrayList<ArrayList<Integer>> worldMask;
    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    public void loadWorldMask(ArrayList<ArrayList<Integer>> mask){
        worldMask = mask;
    }
    public ArrayList<ArrayList<Integer>> getWorldMask(){
        return worldMask;
    }
    public boolean isAvailable(int x, int y){
        return worldMask.get(x).get(y)==0;
    }
    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }

    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> el = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    el.add(e);
                }
            }
        }
        return el;
    }

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }
}
