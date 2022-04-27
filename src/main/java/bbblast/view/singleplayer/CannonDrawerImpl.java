package bbblast.view.singleplayer;

import bbblast.view.singleplayer.assetsloader.AssetsLoader;
import bbblast.view.singleplayer.assetsloader.CachingAssetsLoader;
import bbblast.view.singleplayer.assetsloader.ImageAssetsLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

/**
 * The implementation of CannonDrawer.
 */
public class CannonDrawerImpl implements CannonDrawer {

    private final Canvas canvas;
    private final AssetsLoader<Image> loader;
    private static final String PATH = "Cannon.png";
    private final GraphicsContext gc;

    private static final double CANNONPIVOTHEIGHT = 0.2909090909;
    private static final double CANNONPROPORTION = 8;

    /**
     * @param parentCanvas the canvas where the cannon is drawn.
     * 
     */
    public CannonDrawerImpl(final Canvas parentCanvas) {
        this.canvas = parentCanvas;
        this.gc = canvas.getGraphicsContext2D();
        this.loader = new CachingAssetsLoader<>(new ImageAssetsLoader());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawCannon(final int angle) {
        final var bbCanvasW = this.canvas.getWidth();
        final var bbCanvasH = this.canvas.getHeight();
        final double cw = bbCanvasW / CANNONPROPORTION;
        final double ch = bbCanvasH / CANNONPROPORTION;
        final Image c = loader.load(PATH);
        final double px = bbCanvasW / 2.0;
        final double py = bbCanvasH - CANNONPIVOTHEIGHT * ch;
        final double upperleftx = bbCanvasW / 2.0 - cw / 2.0;
        final double upperlefty = bbCanvasH - ch;
        gc.save();
        final Rotate rotate = new Rotate(-angle+90, px, py);
        this.gc.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(),
                rotate.getTy());
        gc.drawImage(c, upperleftx, upperlefty, cw, ch);
        gc.restore();

    }

}
