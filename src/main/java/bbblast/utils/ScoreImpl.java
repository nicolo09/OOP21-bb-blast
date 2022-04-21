package bbblast.utils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 
 * Score implementation
 *
 */
public class ScoreImpl implements Score, Serializable {
    
    /**
     * Serialization of the class
     */
    private static final long serialVersionUID = -7586564633494845842L;
    private final String name;
    private final int score;
    private final LocalDate date;
    
    /**
     * Return the score of the actual player
     * @param name for the player's name
     * @param score for the player's score
     */
    public ScoreImpl(final String name, final int score) {
        this.name = name;
        this.score = score;
        this.date = LocalDate.now();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getScoreValue() {
        return this.score;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }
    @Override
    public String toString() {
        return "ScoreImpl [name=" + name + ", score=" + score + ", date=" + date + "]";
    }
    @Override
    public int hashCode() {
        return Objects.hash(date, name, score);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ScoreImpl other = (ScoreImpl) obj;
        return Objects.equals(date, other.date) && Objects.equals(name, other.name) && score == other.score;
    }
    
    
    
    
}
