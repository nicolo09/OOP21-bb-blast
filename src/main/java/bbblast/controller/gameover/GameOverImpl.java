package bbblast.controller.gameover;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Implements a GameOver with scores and a timestamp
 */
public class GameOverImpl implements GameOver {

    private final LocalDateTime timestamp;
    private final Map<Integer, Integer> scores;
    /**
     * Creates a new GameOverImpl with now as timestamp.
     * @param scores
     */
    public GameOverImpl(final Map<Integer, Integer> scores) {
        this.timestamp = LocalDateTime.now();
        this.scores = Map.copyOf(scores);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, Integer> getScores() {
        return Map.copyOf(this.scores);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

}
