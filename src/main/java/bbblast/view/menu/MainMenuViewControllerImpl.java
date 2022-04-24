package bbblast.view.menu;

import bbblast.view.View;
import javafx.application.Platform;
/**
 * Implements a {@link MainMenuViewController} referring a main {@link View}.
 */
public class MainMenuViewControllerImpl implements MainMenuViewController {

    private final View mainView;

    /**
     * 
     * @param mainView the main view on which method will be called
     */
    public MainMenuViewControllerImpl(final View mainView) {
        this.mainView = mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startSingleplayer() {
        mainView.startSinglePlayerGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMultiplayer() {
        mainView.startMultiplayerGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startOptionsMenu() {
        mainView.startOptionsMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        Platform.exit();
    }

}
