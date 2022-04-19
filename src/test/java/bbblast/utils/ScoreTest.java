package bbblast.utils;

import java.time.LocalDate;

/**
 * 
 * Score implementation
 *
 */
public class ScoreTest {
    
    private String name = "";
    private int score = 0;
    private LocalDate date = null;
    
    /**
     * Return the score of the actual player
     * @param name for the player's name
     * @param score for the player's score
     * @return 
     */
    public void ScoreImpl(final String name, final int score) {
        this.name = name;
        this.score = score;
        this.date = LocalDate.now();
    }
    /**
     * {@inheritDoc}
     */
    public String getName() {
        return this.name;
    }
    /**
     * {@inheritDoc}
     */
    public int getScoreValue() {
        return this.score;
    }
    /**
     * {@inheritDoc}
     */
    public LocalDate getDate() {
        return this.date;
    }
    
}
