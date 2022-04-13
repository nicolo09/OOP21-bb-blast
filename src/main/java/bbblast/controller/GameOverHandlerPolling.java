package bbblast.controller;

import bbblast.controller.gameloop.Updatable;
import bbblast.model.Model;
import bbblast.view.View;

/**
 * Implementation of {@link GameOverHandler} that generates a {@link GameOver} testing a
 * boolean supplier when updated (see {@link Updatable}) that works as a 0-arguments predicate
 */
public class GameOverHandlerPolling implements GameOverHandler, Updatable {

    private final Model gameModel;
    private final View gameView;
    
    
    public GameOverHandlerPolling(final Model gameModel, final View gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;
    }

    /**
     * Should probably not be called in this implementation, called by the update
     * method.
     * {@inheritDoc}
     */
    @Override
    public boolean checkGameOver() {
        return this.gameModel.isGameOver();
    }

    /**
     * {@inheritDoc}
     * Tells the view to show a gameOverScreen.
     */
    @Override
    public void handleGameOver(final GameOver gameOver) {
        gameView.gameOver(gameOver);
    }

    /**
     * {@inheritDoc}
     * Calls checkGameOver and if a GameOver occurs handles it. Must be called regularly.
     */
    @Override
    public void update() {
        if (this.checkGameOver()) {
            handleGameOver(new GameOverImpl());
        }
    }

}
