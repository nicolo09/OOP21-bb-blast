package bbblast.utils;

import bbblast.model.Bubble;
import bbblast.model.BubblesGrid;
import bbblast.model.GridInfo;

/**
 * 
 * The interface which models a game Level.
 *
 */
public interface Level {

	/**
	 * @return the {@link BubblesGrid} of this level
	 */
	BubblesGrid getGameBubblesGrid();

	/**
	 * @return the {@link GridInfo} modeling the game grid
	 */
	GridInfo getGameGridInfo();

	/**
	 * @return the current score value
	 */
	int getCurrentScore();

	/**
	 * Adds points to the current score
	 */
	void updateScore(int points);

	/**
	 * This method generates random {@link Bubble}s to fill the specified number of
	 * rows.
	 * 
	 * @param rows the number of rows to fill starting from the top
	 */
	void fillGameBubblesGrid(int rows);

}
