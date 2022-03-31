package bbblast.view.singleplayer;

/**The SinglePlayerGame view.*/
public interface SingleplayerGameView {

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

    /** shows the view.
     */
    void show();

    /** updates the view.
      */
    void update();
}
