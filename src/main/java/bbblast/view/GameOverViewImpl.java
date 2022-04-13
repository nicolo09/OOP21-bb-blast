package bbblast.view;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class GameOverViewImpl implements GameOverView {

    private final View mainView;
    private GameOverViewController controller;
    
    public GameOverViewImpl(final View mainView) {
        this.mainView = mainView;
    }

    @Override
    public Scene getScene() {
        //TODO return scene identical to gameview but with grey bubbles and no action listeners
        return null;
    }

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
        if (result.get() == saveButton){
            this.showSaveScoreDialog();
        } 
        mainView.goToMainMenu();
    }

    private void showSaveScoreDialog() {
        final TextInputDialog nameDialog = new TextInputDialog("");
        nameDialog.setTitle("Insert name: ");
        nameDialog.setHeaderText("Salvataggio punteggio");
        nameDialog.setContentText("Inserisci il tuo nome:");

        nameDialog.showAndWait().ifPresent(name -> controller.saveScore(name));
    }

    @Override
    public void setController(final GameOverViewController gameOverController) {
        this.controller = gameOverController;
    }

}
