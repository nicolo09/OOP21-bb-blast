package bbblast.controller.gameover;

import bbblast.controller.Controller;
import bbblast.controller.gameloop.Updatable;
import bbblast.model.Model;
import bbblast.view.View;

/**
 * Implementation of {@link GameOverHandler} that generates a {@link GameOver}
 * asking the model when updated (see {@link Updatable}).
 */
public class LastRowGameOverHandlerPolling extends ViewDelegateGameOverHandler implements GameOverHandler, Updatable {

    private final Model gameModel;
    /**
     * 
     * @param gameModel
     * @param gameView
     * @param mainController
     */
    public LastRowGameOverHandlerPolling(final Model gameModel, final View gameView, final Controller mainController) {
        super(gameView, mainController);
        this.gameModel = gameModel;
    }

    /**
     * Should probably not be called in this implementation, called by the update
     * method, automatically handles a gameover if it occurs. {@inheritDoc}
     */
    @Override
    public boolean checkGameOver() {
        if (gameModel.isLastRowReached()) {
            handleGameOver(new LastRowGameOverImpl(gameModel.getScores()));
            return true;
        } else {
            return false;
        }
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
