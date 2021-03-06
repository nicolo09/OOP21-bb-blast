package bbblast.model.level;

import bbblast.model.BubblesGrid;
import bbblast.model.Cannon;
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
     * @return the {@link Cannon} used in this level
     */
    Cannon getGameCannon();

    /**
     * @return the current score value
     */
    int getCurrentScore();

    /**
     * Adds points to the current score value.
     * 
     * @param points the amount to add to the score
     */
    void updateScore(int points);

    /**
     * This method generates random {@link Bubble}s to fill the specified number of
     * rows. If there are already some rows it moves them down.
     * 
     * @param rows the number of rows to fill starting from the top
     */
    void fillGameBubblesGrid(int rows);

}
