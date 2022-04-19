package bbblast.model;

public interface MovementHandler {

	/**
	 * This method tries to move the latest MovingBubble that has been shot.
	 * @return true if it could move the shot by its speed in a legal position,
	 * false if it couldn't and the MovingBubble must be attached to the grid or there is no MovingBubble set
	 */
	boolean handle();
	
	/**
	 * Sets the new MovingBubble to check
	 * @param shot the MovingBubble that has to be handled
	 */
	void setShot(MovingBubble shot);
}
