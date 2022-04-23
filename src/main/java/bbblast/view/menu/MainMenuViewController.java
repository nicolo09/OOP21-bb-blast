package bbblast.view.menu;

/**
 * 
 * A ViewController for a {@link MainMenuView}.
 *
 */
public interface MainMenuViewController {
    /**
     * Start a singleplayer game.
     */
    void startSingleplayer();

    /**
     * Start a singleplayer game.
     */
    void startMultiplayer();

    /**
     * Shows the options menu.
     */
    void startOptionsMenu();

    /**
     * Quits the game.
     */
    void quit();
}
