package bbblast.model;

import bbblast.utils.Position;

/**
 * 
 * The interface of a Bubble which has a defined speed.
 *
 */
public interface MovingBubble extends Bubble {
	
	/**
	 * Sets the speed of this Bubble.
	 * @param speed it represents the speed on both the x and y axis
	 */
	void setSpeed(Position speed);
	
	/**
	 * @return the speed on the x axis
	 */
	double getSpeedX();
	
	/**
	 * @return the speed on the y axis
	 */
	double getSpeedY();
	
	/**
	 * Moves this Bubble by its speed.
	 */
	void move();
	
	/**
	 * Changes the actual x speed with its opposite.
	 */
	void swapSpeedX();

}
