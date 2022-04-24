package bbblast.view.options;

import javafx.scene.Scene;
/**
 * Interface for an {@link OptionView}.
 */
public interface OptionView {
    /**
     * 
     * @param controller
     */
    void setController(OptionViewController controller);
    /**
     * 
     * @return this view's scene
     */
    Scene getScene();

}
