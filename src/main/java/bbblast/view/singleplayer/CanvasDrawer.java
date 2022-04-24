package bbblast.view.singleplayer;

import java.util.Collection;

import bbblast.model.Bubble;
import bbblast.model.Cannon;
/**
 * 
 * The interface which models a CanvasDrawer, which has the task to draw the game elements.
 *
 */
public interface CanvasDrawer {
	/**
	 * Makes the CanvasDrawer draw the game elements.
	 * @param bubbles the collection of bubbles to draw
	 * @param cannon the cannon to draw
	 */
	void drawOnCanvas(Collection<Bubble> bubbles, Cannon cannon);
	
}
