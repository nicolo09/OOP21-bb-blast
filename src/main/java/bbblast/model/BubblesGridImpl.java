package bbblast.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bbblast.utils.Triplet;
import bbblast.utils.TripletImpl;
import bbblast.utils.TripletIntegerUtility;

/**
 * The implementation of BubblesGrid.
 */
public class BubblesGridImpl implements BubblesGrid {

    private final Map<Triplet<Integer, Integer, Integer>, Bubble> grid;
    private final List<Triplet<Integer, Integer, Integer>> directions = List.of(new TripletImpl<>(1, 0, -1),
            new TripletImpl<>(1, -1, 0), new TripletImpl<>(0, -1, 1), new TripletImpl<>(-1, 0, 1),
            new TripletImpl<>(-1, 1, 0), new TripletImpl<>(0, 1, -1));

    /**
     * This constructor creates an empty BubblesGrid.
     */
    public BubblesGridImpl() {
        this.grid = new HashMap<>();
    }

    /**
     * This constructor creates a BubbleGrid with every bubble contained in the
     * collection.
     * 
     * @param collection from which to read the Bubbles to load in the BubblesGrid
     * 
     */
    public BubblesGridImpl(final Collection<Bubble> collection) {
        this();
        for (final var elem : collection) {
            this.grid.put(this.convertCoords(elem.getCoords()), new BubbleImpl(elem));
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
     * {@inheritDoc} Returns 0 with an empty BubblesGrid.
     */
    @Override
    public int getLastRowY() {
        if (!this.grid.isEmpty()) {
            return this.grid.get(this.grid.entrySet().stream().map(e -> e.getKey())
                    .sorted((t1, t2) -> t1.getY() - t2.getY()).findFirst().get()).getCoords().getY();
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBubble(final Bubble b) {
        this.grid.put(this.convertCoords(b.getCoords()), new BubbleImpl(b));

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

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Bubble> getSameColorNeighbors(final Bubble b) {

        final List<Bubble> list = new ArrayList<>();

        if (!this.grid.isEmpty() && this.grid.containsValue(b) && !list.contains(b)) {
            // We have visited the bubble
            list.add(new BubbleImpl(b));
            final var tripletB = this.grid.entrySet().stream().filter(e -> e.getValue().equals(b)).findFirst().get()
                    .getKey();

            for (final var dir : this.directions) {
                final var tripletNeighbor = TripletIntegerUtility.add(tripletB, dir);
                if (this.grid.containsKey(tripletNeighbor)
                        && this.grid.get(tripletNeighbor).getColor().equals(b.getColor())) {

                    // We visit the neighbor bubbles recursively
                    list.addAll(this.getSameColorNeighbors(this.grid.get(tripletNeighbor)));
                }
            }
        }
        return list;
    }

    /**
     * @param p the 2D position to convert.
     * @return Triplet, the 3D converted position
     */
    private Triplet<Integer, Integer, Integer> convertCoords(final Position p) {
        final int q = p.getY() - (p.getX() - (p.getX() % 2)) / 2;
        final int r = p.getX();
        return new TripletImpl<Integer, Integer, Integer>(q, r, -q - r);
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BubblesGridImpl [grid=" + grid + ", directions=" + directions + "]";
    }
    
    

}
