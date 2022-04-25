package bbblast.model;

import java.util.Collection;
import java.util.Map;

import bbblast.controller.gameloop.Updatable;
import bbblast.model.level.Level;

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
	 * @return the collection of all {@link Bubble}s
	 */
	Collection<Bubble> getBubbles();

	/**
	 * moves the {@link Cannon}.
	 * 
	 * @param angle
	 */
	void moveCannon(int angle);

	/**
	 * makes the {@link Cannon} shoot.
	 */
	void shootCannon();

	/**
	 * @return the angle of the {@link Cannon}
	 */
	int getCannonAngle();

	/**
	 * @return a map with player-score as entries
	 */
	Map<Integer, Integer> getScores();

	/**
	 * switches the {@link Bubble} inside the cannon.
	 */
	void switchBubble();

	/**
	 * Set up this model for a new game.
	 * 
	 * @param grid
	 */
	void startNewGame(GridInfo grid, int fps);

	/**
	 * @return true if {@link Bubble}s has reached last row
	 */
	boolean isLastRowReached();

	/**
	 * @return the current {@link Level}
	 */
	Level getCurrentLevel();

}
