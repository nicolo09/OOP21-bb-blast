package bbblast.view;

import bbblast.controller.Controller;
import bbblast.view.menu.MainMenuView;
import bbblast.view.menu.MainMenuViewController;
import bbblast.view.menu.MainMenuViewImpl;
import bbblast.view.singleplayer.SingleplayerGameView;
import bbblast.view.singleplayer.SingleplayerGameViewController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
        this.controller.startSinglePlayerGame();
        this.stage.setScene(null);
    }
    
    @Override
    public void startMultiplayerGame() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void startOptionsMenu() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void gameOver() {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        final MainMenuViewController mainMenuController = new MainMenuViewControllerImpl(this);
        final MainMenuView mainMenuView = new MainMenuViewImpl();
        mainMenuView.setController(mainMenuController);
        final Scene mainMenuScene = mainMenuView.getScene();

        this.adjustStage(mainMenuScene);
        stage.show();
    }
    
    private void adjustStage(final Scene scene) {
        stage.setScene(scene);
        stage.setMinWidth(scene.getWidth());
        stage.setMinHeight(scene.getHeight());
    }


}
