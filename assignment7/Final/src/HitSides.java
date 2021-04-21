import java.util.ArrayList;

/**
 * HitSides - function in charge of changing block directions from the GameLevel.
 *
 * @author Uriel Schwell
 * @version 21.06.2018
 */
public class HitSides implements HitListener {

    private Block leftBorder;
    private Block rightBorder;
    private ArrayList<Block> enemies;
    private ArrayList<Block> shields;

    /**
     * Constructor.
     *
     * @param leftBorder  our leftmost Border.
     * @param rightBorder our ritghtmost border.
     */
    public HitSides(Block leftBorder, Block rightBorder) {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.enemies = new ArrayList<Block>();
        this.shields = new ArrayList<Block>();
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //if we hit the sides
        if ((beingHit == leftBorder) || (beingHit == rightBorder)) {
            for (Block b : enemies) {
                //we move down one space
                b.moveOneStepY();
                //we change our Horizontal direction of mevement.
                Velocity newV = b.getBlockSpeed();
                newV.flipX();
                b.setBlockSpeed(newV);
            }

        }
    }

    /**
     * adds an enemy.
     *
     * @param enemy -new enemy to add.
     */
    public void addEnemy(Block enemy) {
        this.enemies.add(enemy);
    }

    /**
     * adds a shield.
     *
     * @param shield -nedw shield to add.
     */
    public void addShield(Block shield) {
        this.shields.add(shield);

    }
}
