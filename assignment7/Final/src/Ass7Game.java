import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.LinkedList;
import java.util.List;

/**
 * GameLevel - this class will run the main function and help initialize
 * the Game.
 *
 * @author Uriel Schwell
 * @version 21.06.2018
 */
public class Ass7Game {

    /**
     * main - the Main (starting) function.
     *
     * @param args - user entered arguements
     */

    public static void main(String[] args) {
        List<LevelInformation> levelList = new LinkedList<LevelInformation>();
        levelList.add(new SpaceInvaders());
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor keyBoard = gui.getKeyboardSensor();
        AnimationRunner animation = new AnimationRunner(gui);
        int lives = 3;


///*
//        Task systemExit = new Task() {
//            @Override
//            public Object run() {
//                System.exit(0);
//                return null;
//            }
//        };
//        Task loadHighScore = new Task() {
//            @Override
//            public Object run() {
//                File tempFile = new File("highScores.ser");
//                HighScoresTable tempHighScores = HighScoresTable.loadFromFile(tempFile);
//                Animation highScoreAnimation = new HighScoresAnimation(tempHighScores, "space", keyBoard);
//                Animation stoppableHighScore = new KeyPressStoppableAnimation(keyBoard, "space",
//                        highScoreAnimation);
//                animation.run(stoppableHighScore);
//                return null;
//            }
//        };
//        /*
//        Task playGame = new Task() {
//            @Override
//            public Object run() {
//                GameFlow game = (new GameFlow(levelList, animation, keyBoard, lives));
//                return (runLevels(levelList));
//            }
//        };
//        */
//
//
//        //we create our Menu
//        Menu<Task> menu = new MenuAnimation<Task>(keyBoard, animation);
//        menu.addSelection("h", "High Scores", loadHighScore);
//       // menu.addSelection("p", "Play Game", playGame);
//        menu.addSelection("q", "quit", systemExit);


        GameFlow game = new GameFlow(levelList, animation, keyBoard, lives);
        game.runLevels(levelList);

        //we run the 'menu' and build our game according to that selection
        //animation.run(menu);
        //Task todo = menu.getStatus();
       // todo.run();

    }
}
