package bbblast.controller;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Represents a game over event.
 */
public interface GameOver {
    /**
     * 
     * @return the game mode that generated this {@link GameOver}
     */
    GAME getGameType();
    
    /**
     * 
     * @return a map with the player numbers as keys and their scores as values
     */
    Map<Integer, Integer> scores();
    
    /**
     * 
     * @return this {@link GameOver} creation timestamp
     */
    LocalDateTime timestamp();
}
