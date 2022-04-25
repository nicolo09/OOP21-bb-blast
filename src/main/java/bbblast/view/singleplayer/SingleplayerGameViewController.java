package bbblast.view.singleplayer;

import java.util.Collection;

import bbblast.model.Bubble;
import javafx.scene.input.KeyEvent;

/**
 * The interface of the SinglePlayer Game Controller.
 */
public interface SingleplayerGameViewController {
    
    /**
     * Take a key in input and choose the right action.
     * @param e is the input key.
     */
    void inputCheck(KeyEvent e);
    /**
     * @return the Collection of bubbles.
     */
    Collection<Bubble> getBubbles();
    
    /**
     * @return the score.
     */
    int getScore();

    /**
     * exits the game.
     */
    void exit();
    

}
