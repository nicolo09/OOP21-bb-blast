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
     * @param a the Angle at which the cannon shoots at.
     */
    void cannonShoot(Angle a);

    /**
     * @return the score.
     */
    int getScore();

    /**
     * pauses the game.
     */
    void pause();

    /**
     * exits the game.
     */
    void exit();

}
