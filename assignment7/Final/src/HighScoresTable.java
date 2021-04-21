
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectOutputStream;


/**
 * this class defines a High Score list
 * and all  functions related to it.
 *
 * @author Uriel Schwell
 * @version 03.06.2018
 */
public class HighScoresTable {
    private ArrayList<ScoreInfo> highScoresList;
    private int size;

    /**
     * HighScoresTable -constructor.
     *
     * @param size -maximum size of the High Scores list.
     */
    public HighScoresTable(int size) {
        // this.highScores =
        ArrayList<ScoreInfo> list = new ArrayList<ScoreInfo>();
        this.highScoresList = list;
        this.size = size;
    }

    /**
     * add - a new high score to (maybe) add.
     *
     * @param score -the new HighScore to add.
     */
    public void add(ScoreInfo score) {
        for (int i = 0; i < this.size; i++) {
            if (highScoresList.isEmpty()) {
                this.highScoresList.add(i, score);
                break;
            }
            //once we find the point to insert our new score.
            if (highScoresList.get(i).getScore() < score.getScore()) {
                //we add our current highScore
                this.highScoresList.add(i, score);
                //once we add, we confirm we havent grown too big.
                if (highScoresList.size() > this.size()) {
                    highScoresList.remove(this.size - 1);
                }
                break;
            }
        }
    }

    /**
     * @return this.size -the current size of our list.
     */
    public int size() {
        return highScoresList.size();
    }

    /**
     * get the Scores.
     * @return -list of Scores.
     */
    public ArrayList<ScoreInfo> getList() {
        return this.highScoresList;
    }

    /**
     * get HighScore -get the current high scores.
     *
     * @return -the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return highScoresList;
    }

    /**
     * getRank -return the rank of the given score.
     *
     * @param score -our current score.
     * @return -the index of the current score.
     */
    public int getRank(int score) {
        if (highScoresList.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < this.size(); i++) {
            if (score >= highScoresList.get(i).getScore()) {
                return (i + 1);
            }
        }
        return (size + 1);
    }

    /**
     * clear - resets the table (replaces it).
     */
    public void clear() {
        ArrayList<ScoreInfo> list = new ArrayList<ScoreInfo>(size);
        this.highScoresList = list;
    }


    /**
     * Load the data from the file.
     * @param fileName -filepath/name
     * @throws IOException -in the event we cant find the file
     */
    public void load(File fileName) throws IOException {

        HighScoresTable highScoresTable = null;
        ObjectInputStream objectInputStream = null;
        this.clear();
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(fileName));
            //we replace our members with the loaded information
            this.highScoresList = (ArrayList<ScoreInfo>) objectInputStream.readObject();
        } catch (Exception e) { // Can't find file to open
            System.out.println("Exception error");
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }

    }

    /**
     * Loads a previous file.
     * @param filename -filepath/name.
     * @return -the file.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable table = new HighScoresTable(5);
        try {
            table.load(filename);
        } catch (Exception e) {
            System.out.println("Youve done something horribly wrong");
        }
        return table;
    }

    /**
     * Save our data to the file.
     * @param fileName -filepath.
     * @throws IOException if we fail to save.
     */
    public void save(File fileName) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            if (fileName.createNewFile()) {
                objectOutputStream = new ObjectOutputStream(
                        new FileOutputStream(fileName));
                objectOutputStream.writeObject(this.highScoresList);
            }
        } catch (IOException e) {
            System.out.println("error");
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
    }

}
