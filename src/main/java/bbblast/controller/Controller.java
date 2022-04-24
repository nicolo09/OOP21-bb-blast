package bbblast.controller;

import java.util.Collection;
import java.util.Optional;

import bbblast.model.Bubble;
import bbblast.model.Model;
import bbblast.utils.Score;
import bbblast.utils.Settings;
import bbblast.view.View;

/***
 * The Controller
 */
public interface Controller {

    /**
     * @param v The view associated with the controller.
     */
    void setView(View v);

    /**
     * @param m The model associated with the controller.
     */
    void setModel(Model m);

    /**
     * @return the settings of the game.
     */
    Optional<Settings> loadSettings();

    /**
     * @param s the settings to save.
     * @return true if correctly written, false otherwise
     */
    boolean writeSettings(Settings s);

    /**
     * Starts the SinglePlayer Game.
     */
    void startSinglePlayerGame();
    
    /**
     * Pauses the game.
     */
    void pauseGame();
    
    /**
     * 
     * @return the score
     */
    int getScore();

    /**
     * Saves a score in the leaderboard.
     * @param name the player's name
     */
    void saveScore(Score score);

    /**
     * @return the collection of bubbles in the game.
     */
    Collection<Bubble> getBubbles();

    /**
     * moves the cannon.
     */
    void moveCannon(int angle);

    /**
     * shoots the cannon.
     */
    void shootCannon();

    /**
     * @return the FPS target game is running at
     */
    int getFPS();

    /**
     * Loads the list of saved scores
     * @return saved scores
     */
    Collection<Score> loadScores();
}
