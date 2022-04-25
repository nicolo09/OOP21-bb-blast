package bbblast.controller.gameover;

import bbblast.controller.gameloop.Updatable;
import bbblast.view.View;

public abstract class ViewDelegateGameOverHandler implements GameOverHandler{

    protected final View gameView;

    public ViewDelegateGameOverHandler(final View gameView) {
        this.gameView = gameView;
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
    }

}