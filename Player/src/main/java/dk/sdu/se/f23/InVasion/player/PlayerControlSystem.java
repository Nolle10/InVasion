package dk.sdu.se.f23.InVasion.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dk.sdu.se.f23.InVasion.common.data.Entity;
import dk.sdu.se.f23.InVasion.common.data.GameData;
import dk.sdu.se.f23.InVasion.common.data.ProcessAt;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.data.entityparts.LifePart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.MovingPart;
import dk.sdu.se.f23.InVasion.common.data.entityparts.PositionPart;
import dk.sdu.se.f23.InVasion.common.services.EntityProcessingService;

import java.util.Random;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

public class PlayerControlSystem implements EntityProcessingService {

    @Override
    public void process(GameData data, World world) {

    }

    private void updateShape(Entity entity){
        PositionPart positionPart = entity.getPart(PositionPart.class);
        //call listener on mouse
        Gdx.input.setInputProcessor(MyListener.getInstance());

        float mouseX = MyListener.getInstance().getMousePositionX();
        float mouseY = MyListener.getInstance().getMousePositionY();
        float playerX = positionPart.getX();
        float playerY = positionPart.getY();

        System.out.println("fundet"+mouseX+" "+mouseY);


        entity.setTexture(new Texture(Gdx.files.internal("images/tower.png")));
        /*Sprite region = new Sprite(entity.getTexture());
        region.rotate(90);*/

        SpriteBatch spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        spriteBatch.draw(entity.getTexture(), playerX, playerY);
        spriteBatch.end();

        // old code
        /*SpriteBatch spriteBatch = new SpriteBatch();
        Texture texture = new Texture(Gdx.files.internal("images/tower.png"));


        spriteBatch.begin();
        spriteBatch.draw(texture, 200, 200);
        spriteBatch.end();*/
    }

    @Override
    public void process(GameData data, World world, ProcessAt processTime) {
        for (Entity player : world.getEntities(Player.class)) {
            //PositionPart positionPart = player.getPart(PositionPart.class);
            updateShape(player);

        }

    }

}
