package dk.sdu.se.f23.InVasion.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class World {
    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    private int baseHealth;
    private Point initState;
    private Point goalState;
    private ArrayList<ArrayList<Integer>> worldMask;
    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public void setGoalState(Point p){
        goalState = p;
    }
    public Point getGoalState(){
        return goalState;
    }
    public void setInitState(Point p){
        initState = p;
    }

    public Point getInitState() {
        return initState;
    }
    public void decrementHealth(int damage){
        baseHealth = baseHealth-damage;
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
