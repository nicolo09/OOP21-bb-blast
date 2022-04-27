package bbblast.controller.gameover;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Represents a game over event.
 */
public interface GameOver {

    /**
     * 
     * @return a map with the player numbers as keys and their scores as values
     */
    Map<Integer, Integer> getScores();

    /**
     * 
     * @return this {@link GameOver} creation timestamp
     */
    LocalDateTime getTimestamp();
}
