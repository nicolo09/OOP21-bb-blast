package bbblast.view.menu;

import javafx.scene.Scene;

/**
 * A view for the main menu.
 */
public interface MainMenuView {
    /**
     * 
     * @param controller
     */
    void setController(MainMenuViewController controller);

    /**
     * 
     * @return this view's scene
     */
    Scene getScene();

}
