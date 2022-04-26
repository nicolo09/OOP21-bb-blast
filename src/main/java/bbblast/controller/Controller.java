package bbblast.controller;

import java.util.Collection;
import java.util.Optional;

import bbblast.model.Bubble;
import bbblast.model.GridInfo;
import bbblast.model.Model;
import bbblast.model.level.Level;
import bbblast.utils.Score;
import bbblast.utils.Settings;
import bbblast.view.View;

/***
 * The Controller.
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
	 * 
	 * @param score the current game's score
	 */
	void saveScore(Score score);

	/**
	 * @return the collection of bubbles in the game.
	 */
	Collection<Bubble> getBubbles();

	/**
	 * Moves the cannon.
	 * 
	 * @param angle the new {@link Cannon}'s angle
	 */
	void moveCannon(int angle);

	/**
	 * Shoots the cannon.
	 */
	void shootCannon();
	
	/**
	 * @return the current {@link Cannon}'s angle
	 */
	int getCannonAngle();

	/**
	 * @return the FPS target game is running at.
	 */
	int getFPS();

	/**
	 * Loads the collection of saved scores.
	 * 
	 * @return saved scores
	 */
	Collection<Score> loadScores();

	/**
	 * @return the {@link GridInfo} containing the current game's informations
	 */
	GridInfo getGridInfo();

	/**
	 * Saves the {@link Level} status on a file.
	 * 
	 * @param lvl the current level
	 * 
	 * @return true if saved successfully, false otherwise
	 */
	boolean saveLevel(Level lvl);

	/**
	 * Loads from file the last saved {@link Level}, if there's any.
	 * 
	 * @return the last saved level, if there isn't any it returns an optional empty
	 */
	Optional<Level> loadLevel();
	
	/**
	 * Resets the status of the application.
	 */
	void reset();
}
