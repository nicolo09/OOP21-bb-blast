package bbblast.view;

import javafx.scene.Scene;

public interface GameOverView {

    void setController(GameOverViewController gameOverController);
    
    void showEndDialog();

    Scene getScene();
}
