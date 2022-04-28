package bbblast.view.singleplayer;

import java.util.Collection;

import bbblast.model.Bubble;

/**
 * The interface of the SinglePlayer Game Controller.
 */
public interface SingleplayerGameViewController {

    /**
     * @return the Collection of bubbles.
     */
    Collection<Bubble> getBubbles();

    /**
     * Moves the cannon.
     * 
     * @param angle
     */
    void moveCannon(int angle);

    /**
     * Tells the cannon to shoot.
     */
    void cannonShoot();

    /**
     * @return the score.
     */
    int getScore();

    /**
     * pauses the game.
     */
    void pause();
    
    /**
     * Resumes the game.
     */
    void resume();

    /**
     * exits the game.
     */
    void exit();

    /**
     * 
     * @return the angle the cannon is at.
     */
    int getCannonAngle();

    /**
     * Moves the cannon to the right.
     */
    void moveCannonRight();

    /**
     * Moves the cannon to the left.
     */
    void moveCannonLeft();

}
