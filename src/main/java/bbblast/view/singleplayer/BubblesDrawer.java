package bbblast.view.singleplayer;

import java.util.List;
import bbblast.model.Bubble;

/**
 * Interface that model the bubble drawer.
 */
public interface BubblesDrawer {
    /**
     * draws bubble.
     * @param l list of bubbles
     */
    
    void drawBubbles(List<Bubble> l);
    
}