package bbblast.controller.gameover;

import bbblast.controller.Controller;
import bbblast.view.View;

/**
 * 
 * Abstract {@link GameOverHandler} that handles a {@link GameOver} by telling
 * the view to show a game over screen and resetting the controller. Implement
 * checkGameOver() to define when a gameOver occurs.
 *
 */
public abstract class ViewDelegateGameOverHandler implements GameOverHandler {

    private final View gameView;
    private final Controller controller;

    /**
     * 
     * @param gameView
     * @param mainController
     */
    public ViewDelegateGameOverHandler(final View gameView, final Controller mainController) {
        this.gameView = gameView;
        this.controller = mainController;
    }

    /**
     * Implement this to define what a GameOver is.
     * 
     * @return true if a game over occurred
     */
    public abstract boolean checkGameOver();

    /**
     * {@inheritDoc} Tells the view to show a gameOverScreen.
     */
    @Override
    public void handleGameOver(final GameOver gameOver) {
        gameView.gameOver(gameOver);
        controller.reset();
    }

}
