package bbblast.view;

import bbblast.controller.Controller;
import bbblast.controller.gameloop.Updatable;

/**
 * 
 * Represents main view of the game.
 *
 */
public interface View extends Updatable {
    /**
     * Set the controller associated with this view.
     * @param controller
     */
    void setController(Controller controller);
    /**
     * Starts a singleplayer game.
     */
    void startSinglePlayerGame();
    /**
     * Starts a multiplayer game.
     */
    void startMultiplayerGame();
    /**
     * Shows an option menu
     */
    void startOptionsMenu();
    /**
     * Shows a game over screen.
     */
    void gameOver();
    /**
     * Shows this view.
     */
    void show();

}
