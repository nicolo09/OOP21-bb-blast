package bbblast.view.singleplayer;

import javafx.scene.Scene;

/** The Game view. */
public interface GameView {

    /**
     * starts the music.
     */
    void playMusic();

    /**
     * pauses the music.
     */
    void pauseMusic();

    /**
     * @param e the sound effect.
     */
    void playSoundEffect(Sound e);
    
    /**
     * Sets the controller for this view.
     * @param controller
     */
    void setController(SingleplayerGameViewController controller);

    /**
     * updates the view.
     */
    void update();
    
    Scene getScene();
}
