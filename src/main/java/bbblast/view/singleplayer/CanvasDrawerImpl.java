package bbblast.view.singleplayer;

import java.util.Collection;

import bbblast.model.Bubble;
import bbblast.model.Cannon;
import bbblast.model.GridInfo;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * 
 * Implementation of CanvasDrawer.
 *
 */
public class CanvasDrawerImpl implements CanvasDrawer {

	private final BubblesDrawer bubbleD;
	private final CannonDrawer cannonD;
	private final Canvas canvas;
	private final GraphicsContext gc;

	/**
	 * Creates a new CanvasDrawerImpl.
	 * 
	 * @param canvas where the elements will be drawn
	 * @param infos  the informations to draw the elements on the canvas
	 */
	public CanvasDrawerImpl(final Canvas canvas, final GridInfo infos) {
		this.bubbleD = new BubblesDrawerImpl(canvas, infos);
		this.cannonD = new CannonDrawerImpl(); // TO BE DEFINED
		this.canvas = canvas;
		this.gc = canvas.getGraphicsContext2D();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawOnCanvas(final Collection<Bubble> bubbles, final Cannon cannon) {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getLayoutY());
		bubbleD.drawBubbles(bubbles);
		cannonD.drawCannon(cannon);
	}

}
