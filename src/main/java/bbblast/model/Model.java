package bbblast.model;

import java.util.Collection;
import java.util.Map;

import bbblast.controller.Controller;
import bbblast.controller.gameloop.Updatable;
import bbblast.utils.Score;
import bbblast.utils.Settings;

/**
 * 
 * The Interface of the Model.
 * 
 */
public interface Model extends Updatable {
	
	/**
	 * updates the game values. 
	 */
	void update();

	/**
	 * @return the collection of all Bubbles
	 */
	Collection<Bubble> getBubbles();
	
	/**
	 * moves the cannon.
	 * @param angle 
	 */
	void moveCannon(int angle);

	/**
	 * makes the cannon shoot.
	 */
	void shootCannon();

	/**
	 * @return the angle of the cannon
	 */
	int getCannonAngle();

	/**
	 * @return a map with player-score as entries
	 */
	Map<Integer, Integer> getScores();

	/**
	 * @return true if the BubblesGrid reaches the bottom, false otherwise
	 */
	boolean isGameOver();
	
	/**
	 * switches the Bubble inside the cannon.
	 */
	void switchBubble();

	/**
	 * reads from a file the Settings to load.
	 * @return the Settings to apply to the game
	 */
	Settings loadSettings();

	/**
	 * writes on a file the current Settings.
	 * @param s the current Settings
	 */
	void writeSettings(Settings s);
	
	/**
	 * reads from a file the saved Scores.
	 * @return a collection of Scores from the previous games 
	 */
	Collection<Score> loadScores();
	
	/**
	 * writes on a file the current score.
	 * @param s the current Score
	 */
	void writeScore(Score s);

    void setController(Controller controller);
	
}
