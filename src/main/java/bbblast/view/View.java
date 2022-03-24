package bbblast.view;
/**
 * 
 * Represents main view of the game.
 *
 */
public interface View {
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
     * Shows a game over screen.
     */
    void gameOver();
    /**
     * Shows this view.
     */
    void show();

}
