package bbblast.view.menu;

import bbblast.view.View;
import javafx.application.Platform;

public class MainMenuViewControllerImpl implements MainMenuViewController {

    private final View mainView;
    private MainMenuView menuView;
    
    /**
     * 
     * @param mainView the main view on which method will be called
     */
    public MainMenuViewControllerImpl(final View mainView) {
        this.mainView = mainView;
    }
    
    @Override
    public void setView(final MainMenuView v) {
        this.menuView = v;
    }

    @Override
    public void startSingleplayer() {
        mainView.startSinglePlayerGame();
    }

    @Override
    public void startMultiplayer() {
        mainView.startMultiplayerGame();
    }

    @Override
    public void startOptionsMenu() {
        mainView.startOptionsMenu();
    }

    @Override
    public void quit() {
        Platform.exit();
    }

}
