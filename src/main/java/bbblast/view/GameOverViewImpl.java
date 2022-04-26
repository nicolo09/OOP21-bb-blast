package bbblast.view;

import java.util.Collection;
import java.util.Optional;

import bbblast.model.Bubble;
import bbblast.model.GridInfo;
import bbblast.view.singleplayer.BubbleCanvas;
import bbblast.view.singleplayer.BubblesDrawer;
import bbblast.view.singleplayer.BubblesDrawerImpl;
import bbblast.view.singleplayer.CanvasDrawer;
import bbblast.view.singleplayer.CanvasDrawerImpl;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

/**
 * Implements a {@link GameOverView} showing a dialog with the maximum score and
 * asking if the player wants to save it.
 */
public class GameOverViewImpl implements GameOverView {

    private static final double MINWIDTH = 0;
    private static final double MINHEIGHT = 0;
    private final View mainView;
    private GameOverViewController controller;
    private Scene scene;

    /**
     * 
     * @param mainView the main View instance
     */
    public GameOverViewImpl(final View mainView, final Collection<Bubble> bubbles, final GridInfo grid) {
        this.mainView = mainView;
        final BorderPane root = new BorderPane();
        final BubbleCanvas bubbleCanvas = new BubbleCanvas(grid.getPointsWidth(), grid.getPointsHeight());
        root.setCenter(bubbleCanvas);
        final BubblesDrawer drawer = new BubblesDrawerImpl(bubbleCanvas, grid);
        root.setCenter(bubbleCanvas);
        this.scene = new Scene(root, MINWIDTH, MINHEIGHT);

        // Keep canvas' aspect ratio by binding its properties
        bubbleCanvas.widthProperty().bind(root.heightProperty().divide(1.5));
        bubbleCanvas.heightProperty().bind(root.heightProperty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getScene() {
        return this.scene;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showEndDialog() {
        final Alert dialog = new Alert(AlertType.CONFIRMATION);
        dialog.setTitle("Game over");
        dialog.setHeaderText("Il punteggio è stato: " + controller.getScore());
        dialog.setContentText("Vuoi salvarlo?");

        final ButtonType saveButton = new ButtonType("Salva");
        final ButtonType exitButton = new ButtonType("Esci");

        dialog.getButtonTypes().setAll(saveButton, exitButton);

        final Optional<ButtonType> result = dialog.showAndWait();
        if (result.get() == saveButton) {
            this.showSaveScoreDialog();
        }
        mainView.goToMainMenu();
    }

    /**
     * Show a dialog to save the maximum score.
     */
    private void showSaveScoreDialog() {
        final TextInputDialog nameDialog = new TextInputDialog("");
        nameDialog.setTitle("Insert name: ");
        nameDialog.setHeaderText("Salvataggio punteggio");
        nameDialog.setContentText("Inserisci il tuo nome:");

        nameDialog.showAndWait().ifPresent(name -> controller.saveScore(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final GameOverViewController gameOverController) {
        this.controller = gameOverController;
    }

}
