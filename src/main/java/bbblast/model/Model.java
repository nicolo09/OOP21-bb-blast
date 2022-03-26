package bbblast.model;

import java.util.Collection;

import bbblast.utils.Settings;

/**
 * 
 * The Interface of the Model.
 * 
 */
public interface Model {
	
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
	 */
	void moveCannon();

	/**
	 * makes the cannon shoot.
	 */
	void shootCannon();

	/**
	 * @return the angle of the cannon
	 */
	int getCannonAngle();

	/**
	 * @return the score of the current game
	 */
	int getScore();

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
	
}
