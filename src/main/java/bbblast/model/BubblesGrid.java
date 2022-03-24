package bbblast.model;

import java.util.Collection;

/**
 * 
 * The Interface which models a grid of Bubbles.
 *
 */
public interface BubblesGrid {
	
	/**
	 * @return the collection of Bubbles inside the grid
	 */
	Collection<Bubble> getBubbles();
	
	/**
	 * @return the Y coordinate of the lowest row
	 */
	int getLastRowY();
	
	/**
	 * adds the Bubble in the specified position.
	 * @param p the position of the new Bubble 
	 */
	void addBubble(Position p);
	
	/**
	 * removes the Bubble in the specified position.
	 * @param p the position of the Bubble to remove
	 */
	void removeBubble(Position p);

}
