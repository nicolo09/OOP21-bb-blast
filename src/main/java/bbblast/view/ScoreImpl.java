package bbblast.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

import bbblast.utils.Score;

public class ScoreImpl implements Score {

    private final String name;
    private final int score;
    private final LocalDateTime timestamp;
    
    /**
     * Creates a score with now as timestamp
     * @param name player's name
     * @param score player's score
     */
    public ScoreImpl(final String name, final int score) {
        this.name = name;
        this.score = score;
        this.timestamp = LocalDateTime.now();
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getScoreValue() {
        return this.score;
    }

    @Override
    public LocalDate getDate() {
        return this.timestamp.toLocalDate();
    }

}
