package bbblast.view;

import bbblast.controller.Controller;
import bbblast.controller.GameOver;
import bbblast.view.menu.MainMenuView;
import bbblast.view.menu.MainMenuViewController;
import bbblast.view.menu.MainMenuViewControllerImpl;
import bbblast.view.menu.MainMenuViewImpl;
import bbblast.view.options.OptionView;
import bbblast.view.options.OptionViewController;
import bbblast.view.options.OptionViewControllerImpl;
import bbblast.view.options.OptionViewImpl;
import bbblast.view.singleplayer.GameView;
import bbblast.view.singleplayer.SingleplayerGameViewController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JFXViewImpl implements View {

    private Controller controller;
    private final Stage stage;

    public JFXViewImpl(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public void startSinglePlayerGame() {
        final GameView gameView = null;
        final SingleplayerGameViewController gameViewController = null;
        this.controller.startSinglePlayerGame();
        Platform.runLater(() -> {
            //TODO Create singleplayer view
            this.adjustStageAndSetScene(null);
            stage.show();
        });
    }

    @Override
    public void startMultiplayerGame() {
        // TODO Auto-generated method stub

    }

    @Override
    public void startOptionsMenu() {
        final OptionViewController optionViewController = new OptionViewControllerImpl(controller, this);
        final OptionView optionView = new OptionViewImpl(this, optionViewController);
        final Scene optionScene = optionView.getScene();
        Platform.runLater(() -> {
            this.adjustStageAndSetScene(optionScene);
            stage.show();
        });
    }

    @Override
    public void gameOver(final GameOver gameOverEvent) {
        final GameOverView gameOverView = new GameOverViewImpl(this);
        final GameOverViewController gameOverController = new GameOverViewControllerImpl(
                gameOverEvent.getScores(), (score) -> this.controller.saveScore(score));
        gameOverView.setController(gameOverController);
        Platform.runLater(() -> {
            this.adjustStageAndSetScene(gameOverView.getScene());
            stage.show();
        });
    }

    @Override
    public void show() {
        this.goToMainMenu();
    }

    private void adjustStageAndSetScene(final Scene scene) {
        stage.setScene(scene);
        stage.setMinWidth(scene.getWidth());
        stage.setWidth(scene.getWidth());
        stage.setMinHeight(scene.getHeight());
        stage.setHeight(scene.getHeight());
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
        this.adjustStageAndSetScene(mainMenuScene);
        stage.show();
    }

}
