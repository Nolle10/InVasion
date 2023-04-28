package dk.sdu.se.f23.InVasion.common.data;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class buttonSkin {
    private static Skin skin;

    public static Skin getSkin() {
        if (skin == null) {
            skin = new Skin();

            // Define the font for the buttons
            BitmapFont font = new BitmapFont();

            // Define the colors for the buttons
            Color buttonColor = Color.WHITE;
            Color buttonPressedColor = Color.DARK_GRAY;
            Color buttonHoverColor = Color.LIGHT_GRAY;

            // Create a Drawable for the background of the buttons
            TextureRegion buttonBackgroundRegion = new TextureRegion(new Texture("Common/src/main/resources/button-background.png"));
            TextureRegionDrawable buttonBackground = new TextureRegionDrawable(buttonBackgroundRegion);

            // Create the styles for the TextButton class
            TextButton.TextButtonStyle defaultStyle = new TextButton.TextButtonStyle();
            defaultStyle.font = font;
            defaultStyle.fontColor = buttonColor;
            defaultStyle.up = buttonBackground.tint(buttonColor);
            defaultStyle.down = new TextureRegionDrawable(buttonBackgroundRegion).tint(buttonPressedColor);
            defaultStyle.over = new TextureRegionDrawable(buttonBackgroundRegion).tint(buttonHoverColor);

            // Add the style to the skin
            skin.add("default", defaultStyle, TextButton.TextButtonStyle.class);
        }
        return skin;
    }
}
