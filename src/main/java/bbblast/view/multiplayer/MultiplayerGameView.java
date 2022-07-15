package bbblast.view.multiplayer;

import bbblast.view.singleplayer.SingleplayerGameViewController;
import javafx.scene.Scene;

public interface MultiplayerGameView {
    
    /**
     * Sets the controller for this view.
     * @param controller
     */
    void setControllers(SingleplayerGameViewController controller1, SingleplayerGameViewController controller2);

    /**
     * 
     * @return this view's scene
     */
    Scene getScene();
}
