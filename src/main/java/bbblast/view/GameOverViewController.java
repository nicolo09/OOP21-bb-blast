package bbblast.view;

/**
 * Controller for a {@link GameOverView}.
 */
public interface GameOverViewController {
    /**
     * 
     * @return the game over score.
     */
    int getScore();

    /**
     * Saves the score in the leaderboard.
     * @param name player associated with the score
     */
    void saveScore(String name);

}
