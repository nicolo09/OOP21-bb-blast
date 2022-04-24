package bbblast.view.singleplayer;

import bbblast.model.Cannon;
import bbblast.view.singleplayer.assetsloader.AssetsLoader;
import bbblast.view.singleplayer.assetsloader.CachingAssetsLoader;
import bbblast.view.singleplayer.assetsloader.ImageAssetsLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/**
 * The implementation of CannonDrawer.
 */
public class CannonDrawerImpl implements CannonDrawer {

    private final Canvas canvas;
    private final AssetsLoader<Image> loader;
    private static final String PATH = "Cannon.png";
    private final GraphicsContext gc;

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
    public void drawCannon(final Cannon cannon) {
        final Image c = loader.load(PATH);
        final ImageView iv = new ImageView();
        final double x = (canvas.getWidth() / 2.0) + c.getWidth() / 2.0;
        final double y = (canvas.getHeight() * 4.0 / 5.0) + c.getHeight();
        final Rotate rotate = new Rotate();
        rotate.setPivotX(c.getWidth() / 2);
        rotate.setPivotY(c.getHeight());
        rotate.setAngle(cannon.getAngle());
        iv.getTransforms().add(rotate);
        gc.drawImage(iv.getImage(), x, y);

    }

}
