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
			gc.drawImage(getImg(b), adjustPos(b.getCoords().getX()) * getRateoOnX(),
					adjustPos(b.getCoords().getY()) * getRateoOnY(), getRateoOnX(), getRateoOnY());
		}
	}

	private Image getImg(final Bubble b) {
		return loader.load(b.getColor().toString() + "Bubble.png");
	}

	private double adjustPos(final double coord) {
		return coord - infos.getBubbleRadius();
	}

	private double getRateoOnX() {
		return canvas.getWidth() / infos.getBubbleWidth();
	}

	private double getRateoOnY() {
		return canvas.getHeight() / infos.getBubbleHeight();
	}

}
