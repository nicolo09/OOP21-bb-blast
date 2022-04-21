package bbblast.controller;

import java.util.Optional;

import bbblast.controller.gameloop.Updatable;
import bbblast.model.Model;
import bbblast.view.View;

/**
 * Implementation of {@link GameOverHandler} that generates a {@link GameOver}
 * asking the model when updated (see {@link Updatable}).
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
     * method, automatically handles a gameover if it occurs. {@inheritDoc}
     */
    @Override
    public boolean checkGameOver() {
        final Optional<GameOver> gameOver = gameModel.gameOver();
        if (gameOver.isPresent()) {
            handleGameOver(gameOver.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc} Tells the view to show a gameOverScreen.
     */
    @Override
    public void handleGameOver(final GameOver gameOver) {
        gameView.gameOver(gameOver);
    }

    /**
     * {@inheritDoc} Calls checkGameOver and if a GameOver occurs handles it. Must
     * be called regularly.
     */
    @Override
    public void update() {
        this.checkGameOver();
    }

}
