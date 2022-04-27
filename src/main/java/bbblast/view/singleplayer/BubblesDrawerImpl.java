package bbblast.view.singleplayer;

import java.util.Collection;

import bbblast.model.Bubble;
import bbblast.model.GridInfo;
import bbblast.view.singleplayer.assetsloader.AssetsLoader;
import bbblast.view.singleplayer.assetsloader.CachingAssetsLoader;
import bbblast.view.singleplayer.assetsloader.ImageAssetsLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 
 * Implementation of BubbleDrawer.
 *
 */
public class BubblesDrawerImpl implements BubblesDrawer {

    private final GridInfo infos;
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final AssetsLoader<Image> loader;

    /**
     * Creates a new BubblesDrawer.
     * 
     * @param parent the canvas where the BubblesDrawer will draw
     * @param infos  the informations needed to draw the Bubbles consistently
     */
    public BubblesDrawerImpl(final Canvas parent, final GridInfo infos) {
        this.canvas = parent;
        this.infos = infos;
        this.gc = parent.getGraphicsContext2D();
        this.loader = new CachingAssetsLoader<>(new ImageAssetsLoader());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawBubbles(final Collection<Bubble> coll) {
        for (final var b : coll) {
            // posX and posY are the coordinates which represents the top-left "corner" of the Bubble to draw
            final var posX = b.getCoords().getX() - infos.getBubbleRadius();
            final var posY = b.getCoords().getY() - 2 * infos.getBubbleRadius() / Math.sqrt(3);
            gc.drawImage(getImg(b), posX * getRateoOnX(), posY * getRateoOnY(), 2 * infos.getBubbleRadius() * getRateoOnX(),
                    2 * infos.getBubbleRadius() * getRateoOnY());
        }
    }

    // Gets the image from the AssetsLoader
    private Image getImg(final Bubble b) {
        return loader.load(b.getColor().toString() + "Bubble.png");
    }

    // Calculates the correct width for the image
    private double getRateoOnX() {
        return canvas.getWidth() / infos.getPointsWidth();
    }

    // Calculates the correct height for the image
    private double getRateoOnY() {
        return canvas.getHeight() / infos.getPointsHeight();
    }

}
