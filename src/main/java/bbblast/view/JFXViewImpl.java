package bbblast.view;

import bbblast.controller.Controller;
import bbblast.view.menu.MainMenuView;
import bbblast.view.menu.MainMenuViewController;
import bbblast.view.singleplayer.SingleplayerGameView;
import bbblast.view.singleplayer.SingleplayerGameViewController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JFXViewImpl implements View {

    private Controller controller;
    private final Stage stage;
    
    public JFXViewImpl(final Stage stage){
        this.stage = stage;
    }
    
    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public void startSinglePlayerGame() {
        final SingleplayerGameView gameView = null;
        final SingleplayerGameViewController gameViewController = null;
        gameView
        this.controller.startSinglePlayerGame();
        this.stage.setScene(null);
    }

    @Override
    public void gameOver() {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        final MainMenuViewController mainMenuController = null;
        final Scene mainMenuScene = null;
        stage.setScene(mainMenuScene);
        stage.show();
    }

}
