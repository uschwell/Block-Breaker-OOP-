import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection - this class will define all Sprites
 * that will exist in our GameLevel Environment
 * and all the functions that interact with
 * said sprites.
 *
 * @author Uriel Schwell
 * @version 15.04.2018
 */
public class SpriteCollection {
    //members
    private List<Sprite> spriteList;

    /**
     * SpriteCollection - this function will build the SpriteCollection class.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }


    /**
     * addSprite - this function adds a new sprite to the
     * relevant lists.
     *
     * @param s -the sprite to add.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * notifyAllTimePassed- this function is used to inform all sprites how
     * much game time has passed.
     */
    public void notifyAllTimePassed() {
        int i;
        for (i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).timePassed();
        }

    }

    /**
     * drawAllOn - this function is used to draw all our sprites.
     * @param d - the surface we will be drawing to.
     */
    public void drawAllOn(DrawSurface d) {
        int i;
        for (i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).drawOn(d);
        }

    }


    /**
     * objectList - this function returns the list of Sprite objects that
     * exist in our current GameLevel.
     *
     * @return this.collidableObjects- the list of sprite
     * objects
     */
    public List<Sprite> objectList() {
        return this.spriteList;
    }



}
