package bbblast.controller.gameover;

import bbblast.controller.Controller;
import bbblast.controller.gameloop.Updatable;
import bbblast.view.View;

public abstract class ViewDelegateGameOverHandler implements GameOverHandler{

    protected final View gameView;
    private final Controller controller;

    public ViewDelegateGameOverHandler(final View gameView, final Controller mainController) {
        this.gameView = gameView;
        this.controller = mainController;
    }
    
    /**
     * Implement this to define what a GameOver is.
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