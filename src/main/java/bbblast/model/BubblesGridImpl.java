package bbblast.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import bbblast.utils.Triplet;
import bbblast.utils.TripletImpl;
import bbblast.utils.TripletIntegerUtility;
import bbblast.utils.Position;

/**
 * The implementation of BubblesGrid.
 */
public class BubblesGridImpl implements BubblesGrid {

    private final Map<Triplet<Integer, Integer, Integer>, Bubble> grid;
    private final List<Triplet<Integer, Integer, Integer>> directions = List.of(new TripletImpl<>(1, 0, -1),
            new TripletImpl<>(1, -1, 0), new TripletImpl<>(0, -1, 1), new TripletImpl<>(-1, 0, 1),
            new TripletImpl<>(-1, 1, 0), new TripletImpl<>(0, 1, -1));
    private final List<Bubble> neighborsList = new ArrayList<>();

    /**
     * This constructor creates an empty BubblesGrid, with bubble size 1.
     */
    public BubblesGridImpl() {
        this.grid = new HashMap<>();
    }

    /**
     * This constructor creates a BubbleGrid with every bubble contained in the
     * collection.
     * 
     * @param collection from which to read the Bubbles to load in the BubblesGrid.
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
    public double getLastRowY() {
        if (!this.grid.isEmpty()) {
            return this.grid.get(this.grid.entrySet().stream().map(e -> e.getKey())
                    .sorted((t1, t2) -> t2.getY() - t1.getY()).findFirst().get()).getCoords().getY();
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBubble(final Bubble b) {
        if (!this.grid.containsValue(b)) {
            this.grid.put(this.convertCoords(b.getCoords()), new BubbleImpl(b));
        }

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
        this.neighborsList.clear();
        if (!this.grid.isEmpty() && this.grid.containsValue(b)) {
            this.getSameColorNeighborsRecursive(b);
        }

        return List.copyOf(this.neighborsList);
    }

    /**
     * This function is the recursive method that fills neighborsList with all the neighbors of b
     * @param b the bubble from where to start the search
     */
    private void getSameColorNeighborsRecursive(final Bubble b) {
        if (this.grid.containsValue(b) && !this.neighborsList.contains(b)) {
            // We have visited the bubble
            this.neighborsList.add(new BubbleImpl(b));
            final var tripletB = this.grid.entrySet().stream().filter(e -> e.getValue().equals(b)).findFirst().get()
                    .getKey();

            for (final var dir : this.directions) {
                final var tripletNeighbor = TripletIntegerUtility.add(tripletB, dir);
                if (this.grid.containsKey(tripletNeighbor)
                        && this.grid.get(tripletNeighbor).getColor().equals(b.getColor())) {

                    // We visit the neighbor bubbles recursively
                    this.getSameColorNeighborsRecursive(this.grid.get(tripletNeighbor));
                }
            }
        }
    }

    /**
     * @param p the 2D position to convert.
     * @return Triplet, the 3D converted position
     */
    private Triplet<Integer, Integer, Integer> convertCoords(final Position p) {
        return new TripletImpl<Integer, Integer, Integer>((int) Math.round(p.getX()), (int) Math.round(p.getY()),
                -(int) Math.round(p.getX()) - (int) Math.round(p.getY()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(grid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BubblesGridImpl other = (BubblesGridImpl) obj;
        return this.getBubbles().containsAll(other.getBubbles()) && other.getBubbles().containsAll(this.getBubbles());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BubblesGridImpl [grid=" + grid + "]";
    }

}
