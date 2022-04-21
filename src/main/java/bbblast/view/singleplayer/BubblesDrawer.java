package bbblast.view.singleplayer;

import java.util.Collection;
import bbblast.model.Bubble;

/**
 * Interface that model the bubble drawer.
 */
public interface BubblesDrawer {
    /**
     * draws bubble.
     * @param coll collection of bubbles
     */
    void drawBubbles(Collection<Bubble> coll);
    
}