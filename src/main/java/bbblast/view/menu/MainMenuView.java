package bbblast.view.menu;

import javafx.scene.Scene;

public interface MainMenuView {
    
    void setController(MainMenuViewController controller);
    
    Scene getScene();
    
    void show();

}
