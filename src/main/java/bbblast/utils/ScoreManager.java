package bbblast.utils;

import java.util.Collection;

/**
 * 
 * The interface which models a Score manager
 *
 */
public interface ScoreManager {
	
	/**
	 * writes on a file the current score. 
	 * @param s the Score of the last game
	 */
	void save(Score s);
	
	/**
	 * reads from a file the previous Scores. 
	 * @return the collection of all saved Scores
	 */
	Collection<Score> load();
	
	/**
	 * deletes all saved Scores.
	 */
	void resetScores();

}
