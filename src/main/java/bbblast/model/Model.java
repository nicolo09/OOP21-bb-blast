package bbblast.model;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

<<<<<<< HEAD
import bbblast.controller.Controller;
import bbblast.controller.gameloop.Updatable;
import bbblast.controller.gameover.GameOver;
import bbblast.utils.Score;
=======
import bbblast.controller.gameloop.Updatable;
import bbblast.controller.gameover.GameOver;
>>>>>>> 4844ea8517119d1b3602816160bb17f280f07695

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
	 * switches the Bubble inside the cannon.
	 */
	void switchBubble();

	/**
	 * Set up this model for a new game
	 * @param grid
	 */
    void startNewGame(GridInfo grid, int fps);

    /**
     * Returns true if bubbles has reached last row
     */
    boolean isLastRowReached();
	
}
