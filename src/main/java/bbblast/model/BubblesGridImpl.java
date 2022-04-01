package bbblast.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bbblast.utils.Triplet;

public class BubblesGridImpl implements BubblesGrid {

    private final Map<Triplet<Integer, Integer, Integer>, Bubble> grid;

    /**
     * This constructor creates an empty BubbleGrid.
     */
    public BubblesGridImpl() {
        this.grid = new HashMap<>();
    }

    /**
     * This constructor creates a BubbleGrid with every bubble contained in the
     * list.
     */
    public BubblesGridImpl(final List<Bubble> list) {
        this();
        for (final var elem : list) {
            //TODO: decide if method addBubble should be Overridable or not
            this.addBubble(elem.getCoords());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Bubble> getBubbles() {
        return List.copyOf(this.grid.values());
    }

    /**
     * {@inheritDoc} Cannot be called on an empty BubbleGrid.
     */
    @Override
    public int getLastRowY() {
        final var lastBB = getBubbles().stream().findFirst().get();
        // This object is null only if the grid is empty
        // TODO: Decide how to implement the empty grid in this method

        for (final var bubble : this.grid.values()) {
            if (lastBB.getCoords().getY() > bubble.getCoords().getY()) {
                lastBB = new BubbleImpl(bubble);
            }
        }
        return lastBB.getCoords().getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBubble(final Position p) {
        // TODO: Implement this method, but it needs the bubble, not only the position

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeBubble(final Position p) {
        final var iterator = this.grid.entrySet().iterator();
        while (iterator.hasNext()) {
            final var entry = iterator.next();
            if (entry.getValue().getCoords().equals(p)) {
                iterator.remove();
            }
        }
    }

}
