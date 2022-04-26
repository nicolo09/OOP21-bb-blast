package bbblast.view.singleplayer;

import javafx.scene.canvas.Canvas;

public class BubbleCanvas extends Canvas {
    
    public BubbleCanvas(final double width, final double height) {
        super(width, height);
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(final double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(final double width) {
        return getHeight();
    }

}
