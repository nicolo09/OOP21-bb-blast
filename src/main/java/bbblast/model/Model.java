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
     * shoot the cannon in multiplayer games
     */
    void multiShootCannon();

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
     * @param grid the new game grid
     * @param fps  the FPS game is running at, will be used to calculate bubble's
     *             speed
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

    /**
     * Reset the status of this model.
     */
    void reset();

    /**
     * Create new rows on the screen
     * @param number is the number of rows to create
     */
    void rowsDown(int number);

    /**
     * Set the other player model
     * @param model is the other player model
     */
    void setOtherModel(Model model);
}
