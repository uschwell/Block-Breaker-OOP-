import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

/**
 * BackgroundSpaceInvaders - this class will define the the background
 * for a "Space Invaders" level.
 *
 * @author Uriel Schwell
 * @version 21.06.2018
 */
public class BackgroundSpaceInvaders implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        //we draw a background in case the background Image doesnt work
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 30, 800, 600);
        try {
            Image image = ImageIO.read(new File("src/motherShip.jpg"));
            d.drawImage(0, 0, image);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        d.drawCircle(400, 210, 75);
        d.drawCircle(400, 210, 50);
    }

    @Override
    public void timePassed() {

    }
}
