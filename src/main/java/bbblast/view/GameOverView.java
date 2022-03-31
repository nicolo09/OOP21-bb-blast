package bbblast.view;

import javafx.scene.Scene;

public interface GameOverView {

    void setController(GameOverViewController gameOverController);
    
    Scene getScene();

    void showEndDialog();
}
