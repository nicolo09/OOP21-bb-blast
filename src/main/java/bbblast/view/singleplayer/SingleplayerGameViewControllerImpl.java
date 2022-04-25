package bbblast.view.singleplayer;

import java.util.Collection;

import bbblast.controller.Controller;
import bbblast.model.Bubble;
import javafx.scene.input.KeyEvent;

public class SingleplayerGameViewControllerImpl implements SingleplayerGameViewController {
    
    private Controller mainController;
    private static final int MOV = 5;

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
    
    @Override
    public Collection<Bubble> getBubbles() {
        return mainController.getBubbles();
    }

    @Override
    public int getScore() {
        return mainController.getScore();
    }

    @Override
    public void exit() {
        // TODO        
    }


}
