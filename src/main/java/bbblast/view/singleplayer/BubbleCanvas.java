package bbblast.view.singleplayer;

import javafx.scene.canvas.Canvas;

/**
 * Resizable {@link Canvas} for bubble drawing.
 */
public class BubbleCanvas extends Canvas {
    /**
     * Creates a new BubbleCanvas.
     * 
     * @param width
     * @param height
     */
    public BubbleCanvas(final double width, final double height) {
        super(width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isResizable() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double prefWidth(final double height) {
        return getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double prefHeight(final double width) {
        return getHeight();
    }

}
