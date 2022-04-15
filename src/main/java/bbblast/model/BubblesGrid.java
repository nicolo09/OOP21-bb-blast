package bbblast.model;

import java.util.Collection;

import bbblast.utils.Position;

/**
 * 
 * The Interface which models a grid of Bubbles.
 *
 */
public interface BubblesGrid {

    /**
     * @return the collection of Bubbles inside the grid.
     */
    Collection<Bubble> getBubbles();

    /**
     * @return the Y coordinate of the lowest row.
     */
    double getLastRowY();

    /**
     * @return true if the lowest bubble is at the end of the grid.
     */
    boolean endReached();

    /**
     * adds the Bubble, if it isn't already part of the BubblesGrid.
     * 
     * @param b the new Bubble
     */
    void addBubble(Bubble b);

    /**
     * removes the Bubble in the specified position.
     * 
     * @param p the position of the Bubble to remove.
     */
    void removeBubble(Position p);

    /**
     * @param b the bubble to test if it can be attached to the grid.
     * @return true if bubble can be attached.
     */
    boolean isBubbleAttachable(Bubble b);

    /**
     * @param b the Bubble to search its neighbors.
     * @return the collection of same color neighbors as b.
     */
    Collection<Bubble> getSameColorNeighbors(Bubble b);

    /**
     * @param rows the number of rows down the bubbles are translated.
     */
    void moveBubblesDown(int rows);

    /**
     * @return the collection of unconnected bubbles, meaning bubbles that aren't
     *         connected to the top of the grid nor their neighbors are.
     */
    Collection<Bubble> checkForUnconnectedBubbles();

    /**
     * @param the position of the bubble to delete. Removes all the bubbles that are
     *            floating after deletion.
     */
    void removeBubblesCascading(Position p);

    /**
     * Deletes all the unconnected bubbles.
     */
    void removeUnconnectedBubbles();

}
