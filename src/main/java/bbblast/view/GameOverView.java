package bbblast.view;

import javafx.scene.Scene;
/**
 * View shown when a game over occurs.
 */
public interface GameOverView {
    /**
     * Sets the GameOverViewController for this view.
     * @param gameOverController
     */
    void setController(GameOverViewController gameOverController);
    /**
     * Show a dialog with the game over informations.
     */
    void showEndDialog();
    /**
     * 
     * @return this view's scene
     */
    Scene getScene();
}
