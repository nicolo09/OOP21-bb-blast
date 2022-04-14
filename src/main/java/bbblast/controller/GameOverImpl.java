package bbblast.controller;

import java.time.LocalDateTime;
import java.util.Map;

public class GameOverImpl implements GameOver {

    private final LocalDateTime timestamp;
    private final Map<Integer, Integer> scores;
    
    public GameOverImpl(final Map<Integer, Integer> scores) {
        this.timestamp = LocalDateTime.now();
        this.scores = Map.copyOf(scores);
        
    }
    
    @Override
    public Map<Integer, Integer> getScores() {
        return Map.copyOf(this.scores);
    }

    @Override
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

}
