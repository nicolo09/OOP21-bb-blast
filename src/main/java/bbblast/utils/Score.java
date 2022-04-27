package bbblast.utils;

import java.time.LocalDate;
/**
 * 
 * The interface which models a Score.
 *
 */
public interface Score {
	
	/** 
	 * @return the name of the player who got this Score
	 */
	String getName();
	
	/**
	 * @return the Score's numeric value
	 */
	int getScoreValue();
	
	/**
	 * @return the date when the player got this Score 
	 */
	LocalDate getDate();

}
