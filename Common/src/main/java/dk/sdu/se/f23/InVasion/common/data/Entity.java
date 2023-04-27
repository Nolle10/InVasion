package dk.sdu.se.f23.InVasion.common.data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.se.f23.InVasion.common.data.entityparts.EntityPart;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity {
    private final UUID ID = UUID.randomUUID();

    private Texture texture;
    private SpriteBatch spriteBatch;
    @Deprecated
    private float[] shapeX = new float[4];
    @Deprecated
    private float[] shapeY = new float[4];
    @Deprecated
    private float radius;
    private Map<Class, EntityPart> parts;

    public Entity() {
        parts = new ConcurrentHashMap<>();
    }

    public void add(EntityPart part) {
        parts.put(part.getClass(), part);
    }

    public void remove(Class partClass) {
        parts.remove(partClass);
    }

    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
    public void setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    @Deprecated
    public void setRadius(float r){
        this.radius = r;
    }
    @Deprecated
    public float getRadius(){
        return radius;
    }

    public String getID() {
        return ID.toString();
    }

    @Deprecated
    public float[] getShapeX() {
        return shapeX;
    }

    @Deprecated
    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }
    @Deprecated
    public float[] getShapeY() {
        return shapeY;
    }
    @Deprecated
    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }

}
