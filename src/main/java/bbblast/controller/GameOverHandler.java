package bbblast.controller;

/**
 * Represent an handler for GameOvers
 */
public interface GameOverHandler {
    /**
     * 
     * @return true if a GameOver has occurred
     */
    boolean checkGameOver();
    /**
    * 
    * @param gameOver the {@link GameOver} to handle
    */
    void handleGameOver(GameOver gameOver);

}
