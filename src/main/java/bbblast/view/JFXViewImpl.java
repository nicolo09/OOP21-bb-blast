package bbblast.view;

import java.util.ArrayList;
import java.util.List;

import bbblast.controller.Controller;
import bbblast.controller.gameloop.Updatable;
import bbblast.controller.gameover.GameOver;
import bbblast.view.menu.MainMenuView;
import bbblast.view.menu.MainMenuViewController;
import bbblast.view.menu.MainMenuViewControllerImpl;
import bbblast.view.menu.MainMenuViewImpl;
import bbblast.view.options.OptionView;
import bbblast.view.options.OptionViewController;
import bbblast.view.options.OptionViewControllerImpl;
import bbblast.view.options.OptionViewImpl;
import bbblast.view.singleplayer.SingleplayerGameViewController;
import bbblast.view.singleplayer.SingleplayerGameViewControllerImpl;
import bbblast.view.singleplayer.SingleplayerGameViewImpl;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * JavaFX implementation of a view.
 */
public class JFXViewImpl implements View {

    private Controller controller;
    private final Stage stage;
    private final List<Updatable> updatable = new ArrayList<>();

    /**
     * 
     * @param stage the main application stage
     */
    public JFXViewImpl(final Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(a -> {
            System.exit(0);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startSinglePlayerGame() {
        this.controller.startSinglePlayerGame();
        final SingleplayerGameViewImpl gameView = new SingleplayerGameViewImpl(this.controller.getGridInfo());
        this.updatable.add(gameView);
        final SingleplayerGameViewController gameViewController = new SingleplayerGameViewControllerImpl(controller);
        gameView.setController(gameViewController);
        this.adjustStageAndSetScene(gameView.getScene());
        Platform.runLater(() -> {
            stage.show();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMultiplayerGame() {
        // TODO Implement multiplayer
        this.showError("Not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startOptionsMenu() {
        final OptionViewController optionViewController = new OptionViewControllerImpl(controller, this);
        final OptionView optionView = new OptionViewImpl(this, optionViewController);
        final Scene optionScene = optionView.getScene();
        this.adjustStageAndSetScene(optionScene);
        Platform.runLater(() -> {
            stage.show();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameOver(final GameOver gameOverEvent) {
        final GameOverView gameOverView = new GameOverViewImpl(this, this.controller.getBubbles(),
                this.controller.getGridInfo());
        final GameOverViewController gameOverController = new GameOverViewControllerImpl(gameOverEvent.getScores(),
                (score) -> this.controller.saveScore(score));
        gameOverView.setController(gameOverController);
        this.adjustStageAndSetScene(gameOverView.getScene());
        Platform.runLater(() -> {
            stage.show();
            gameOverView.showEndDialog();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        this.goToMainMenu();
    }

    /**
     * Sets stage's minimum width and height to scene size and set scene as stage's
     * scene. It is executed in JavaFX thread with Platform.runLater
     * 
     * @param scene
     */
    private void adjustStageAndSetScene(final Scene scene) {
        Platform.runLater(() -> {
            final var width = stage.getWidth();
            final var height = stage.getHeight();
            stage.setMinWidth(scene.getWidth());
            // stage.setWidth(scene.getWidth());
            stage.setMinHeight(scene.getHeight());
            // stage.setHeight(scene.getHeight());
            stage.setScene(scene);
            stage.setWidth(scene.getWidth() < width ? width : scene.getWidth());
            stage.setHeight(scene.getHeight() < height ? height : scene.getHeight());
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.updatable.forEach(a -> a.update());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToMainMenu() {
        final MainMenuViewController mainMenuController = new MainMenuViewControllerImpl(this);
        final MainMenuView mainMenuView = new MainMenuViewImpl();
        mainMenuView.setController(mainMenuController);
        final Scene mainMenuScene = mainMenuView.getScene();
        this.adjustStageAndSetScene(mainMenuScene);
        Platform.runLater(() -> {
            stage.show();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showError(final String error) {
        final Alert alert = new Alert(AlertType.ERROR, error);
        alert.show();
    }

}
