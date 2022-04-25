package bbblast.view.singleplayer;

import java.util.Collection;

import bbblast.controller.Controller;
import bbblast.model.Bubble;
import bbblast.model.GridInfo;
import javafx.scene.input.KeyEvent;

public class SingleplayerGameViewControllerImpl implements SingleplayerGameViewController {
    
    private Controller mainController;
    private static final int MOV = 5;
    /**
     * Check the input key and do the right action.
     */
    @Override
    public void inputCheck(final KeyEvent e) {
        
        switch (e.getCode()) {
        
        case LEFT:
            mainController.moveCannon(mainController.getCannonAngle() + MOV);
            break;
        case RIGHT:
            mainController.moveCannon(mainController.getCannonAngle() - MOV);
            break;
        case SPACE:
            mainController.shootCannon();
            break;
        case ESCAPE:
            mainController.pauseGame();
            break;
        default:
            break;
        }
    }
    /**
     * return a collection of bubbles.
     */
    @Override
    public Collection<Bubble> getBubbles() {
        return mainController.getBubbles();
    }
    /**
     * return the score.
     */
    @Override
    public int getScore() {
        return mainController.getScore();
    }

    @Override
    public void exit() {
        // TODO        
    }

    @Override
    public GridInfo getGridInfo() {
        return mainController.getGridInfo();
    }
    /**
     * Return the cannon angle.
     */
    @Override
    public int getCannonAngle() {
        return mainController.getCannonAngle();
    }


}
