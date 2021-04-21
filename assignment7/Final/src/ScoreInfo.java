import java.io.Serializable;

/**
 * this class defines a our score information
 * and all  functions related to it.
 *
 * @author Uriel Schwell
 * @version 03.06.2018
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * ScoreInfor -constructor.
     *
     * @param name  -HighScore name.
     * @param score -the HighScore.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * getName - return the name.
     *
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * getScore - return the highscore.
     *
     * @return the score.
     */
    public int getScore() {
        return this.score;
    }

}
