package bbblast.model;

import java.util.Optional;

/**
 * 
 * The interface which models a MovementHandler that handles the movements on a 2D surface.
 *
 */
public interface MovementHandler {
	/**
	 * This method tries to move the latest MovingBubble that has been shot.
	 * @return true if it could move the shot by its speed in a legal position,
	 * false if it couldn't and the MovingBubble must be attached to the grid or there is no MovingBubble set
	 */
	boolean handle();
	
	/**
	 * Sets the new {@link MovingBubble} to check.
	 * @param shot the MovingBubble that has to be handled
	 */
	void setShot(MovingBubble shot);
	
	/**
	 * Gets the currently checked {@link MovingBubble}
	 * @return an {@link Optional} of the currently moving shot or optional empty if no shot is set
	 */
	Optional<MovingBubble> getShot(); 
}
