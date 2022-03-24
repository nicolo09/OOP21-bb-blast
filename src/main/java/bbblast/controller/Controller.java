package bbblast.controller;

import java.util.Collection;

import bbblast.model.Bubble;
import bbblast.model.Model;
import bbblast.utils.Settings;
import bbblast.view.View;

/***
 * The Controller
 */
public interface Controller {

    /**
     * @param v The view associated with the controller.
     */
    void setView(View v);

    /**
     * @param m The model associated with the controller.
     */
    void setModel(Model m);

    /**
     * @return the settings associated with the controller.
     */
    Settings loadSettings();

    /**
     * @param s the setting written on file.
     */
    void writeSettings(Settings s);

    /**
     * Starts the SinglePlayer Game.
     */
    void startSinglePlayerGame();

    /**
     * @return the collection of bubbles in the game.
     */
    Collection<Bubble> getBubbles();

    /**
     * moves the cannon.
     */
    void moveCannon();

    /**
     * shoots the cannon.
     */
    void shootCannon();

}
