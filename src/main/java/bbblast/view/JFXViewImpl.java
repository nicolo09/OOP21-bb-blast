package bbblast.view;

import bbblast.controller.Controller;
import bbblast.view.menu.MainMenuView;
import bbblast.view.menu.MainMenuViewController;
import bbblast.view.menu.MainMenuViewControllerImpl;
import bbblast.view.menu.MainMenuViewImpl;
import bbblast.view.options.OptionView;
import bbblast.view.options.OptionViewController;
import bbblast.view.options.OptionViewImpl;
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
        final OptionView optionView = new OptionViewImpl(this);
        final OptionViewController optionViewController = new OptionViewControllerImpl();
        optionView.setController(optionViewController);
        final Scene optionScene = optionView.getScene();
        this.adjustStage(optionScene);
        stage.show();
    }
    
    @Override
    public void gameOver() {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        this.goToMainMenu();
    }
    
    private void adjustStage(final Scene scene) {
        stage.setScene(scene);
        stage.setMinWidth(scene.getWidth());
        stage.setMinHeight(scene.getHeight());
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void goToMainMenu() {
        final MainMenuViewController mainMenuController = new MainMenuViewControllerImpl(this);
        final MainMenuView mainMenuView = new MainMenuViewImpl();
        mainMenuView.setController(mainMenuController);
        final Scene mainMenuScene = mainMenuView.getScene();
        this.adjustStage(mainMenuScene);
        stage.show();
    }


}
