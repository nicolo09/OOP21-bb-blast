package bbblast.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import bbblast.utils.Triplet;
import bbblast.utils.TripletImpl;
import bbblast.utils.TripletIntegerUtility;
import bbblast.utils.Position;
import bbblast.utils.PositionImpl;

/**
 * The implementation of BubblesGrid.
 */
public class BubblesGridImpl implements BubblesGrid {

    private final Map<Triplet<Integer, Integer, Integer>, Bubble> grid;
    private final List<Triplet<Integer, Integer, Integer>> directions = List.of(new TripletImpl<>(1, 0, -1),
            new TripletImpl<>(1, -1, 0), new TripletImpl<>(0, -1, 1), new TripletImpl<>(-1, 0, 1),
            new TripletImpl<>(-1, 1, 0), new TripletImpl<>(0, 1, -1));
    private final List<Bubble> neighborsList = new ArrayList<>();
    private final GridInfo info;
    private final double size;

    /**
     * @param info the GridInfo that defines the dimentions of the grid. This
     *             constructor creates an empty BubblesGrid.
     */
    public BubblesGridImpl(final GridInfo info) {
        this.info = info;
        this.grid = new HashMap<>();
        this.size = this.info.getPointsHeight() / this.info.getBubbleHeight() / 2.0;
    }

    /**
     * This constructor creates a BubbleGrid with every bubble contained in the
     * collection. Could return a grid with unconnected bubbles, if the collection
     * contains them.
     * 
     * @param collection from which to read the Bubbles to load in the BubblesGrid.
     * @param info       the GridInfo that defines the dimentions of the grid.
     * 
     */
    public BubblesGridImpl(final Collection<Bubble> collection, final GridInfo info) {
        this(info);
        for (final var elem : collection) {
            final Triplet<Integer, Integer, Integer> triplet = this.convertCoords(elem.getCoords());
            final Position roundedPosition = this.roundCoords(triplet);
            this.grid.put(triplet, new BubbleImpl(roundedPosition, elem.getColor()));
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
    public boolean endReached() {
        return this.getLastRowY() >= this.info.getBubbleHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBubble(final Bubble b) {
        if (!this.grid.containsKey(this.convertCoords(b.getCoords())) && this.isBubbleAttachable(b)) {

            final Triplet<Integer, Integer, Integer> triplet = this.convertCoords(b.getCoords());
            final Position roundedPosition = this.roundCoords(triplet);
            this.grid.put(this.convertCoords(b.getCoords()), new BubbleImpl(roundedPosition, b.getColor()));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeBubble(final Position p) {
        final var t = this.convertCoords(p);
        final var iterator = this.grid.entrySet().iterator();
        while (iterator.hasNext()) {
            final var entry = iterator.next();
            if (entry.getKey().equals(t)) {
                iterator.remove();
                return;
            }
        }
    }

    /**
     * {@inheritDoc} The bubbles can connect to the grid either if there is a
     * neighbor bubble already present or if they connect to the top.
     */
    @Override
    public boolean isBubbleAttachable(final Bubble b) {
        if (!this.grid.containsValue(b)) {
            final var tripletB = this.convertCoords(b.getCoords());
            final Position roundedPosition = this.roundCoords(tripletB);
            // The rounded position is inside the grid
            if (roundedPosition.getX() < this.info.getPointsWidth()
                    && roundedPosition.getY() < this.info.getPointsHeight()) {
                if (tripletB.getY() == 0) {
                    return true;
                }
                if (!this.grid.isEmpty()) {
                    for (final var dir : this.directions) {
                        final var tripletNeighbor = TripletIntegerUtility.add(tripletB, dir);
                        if (this.grid.containsKey(tripletNeighbor)) {
                            return true;
                        }
                    }
                }
            }

        }
        return false;
    }

    /**
     * {@inheritDoc} Only works with positive numbers
     */
    @Override
    public void moveBubblesDown(final int rows) {
        if (rows > 0) {
            final Map<Triplet<Integer, Integer, Integer>, Bubble> gridTemporary = new HashMap<>();
            for (final var entry : this.grid.entrySet()) {
                final Bubble b = entry.getValue();
                b.moveBy(new PositionImpl(0, rows*this.size*2));
                gridTemporary.put(this.convertCoords(b.getCoords()), b);
            }
            this.grid.clear();
            this.grid.putAll(gridTemporary);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Bubble> checkForUnconnectedBubbles() {
        // The neighborsList hosts all the bubbles that are reachable by a bubble
        // connected to the top
        this.neighborsList.clear();
        if (!this.grid.isEmpty()) {
            for (final var elem : this.grid.entrySet().stream().map(e -> e.getKey()).filter(k -> k.getY() == 0)
                    .collect(Collectors.toList())) {
                checkForUnconnectedBubblesRecursive(elem);
            }
        }
        // neighborsList contains now all the connected bubbles.
        final Map<Triplet<Integer, Integer, Integer>, Bubble> difference = new HashMap<>(this.grid);
        difference.entrySet().removeIf(e -> this.neighborsList.contains(e.getValue()));
        return List.copyOf(difference.values());
    }

    private void checkForUnconnectedBubblesRecursive(final Triplet<Integer, Integer, Integer> b) {
        this.neighborsList.add(this.grid.get(b));
        for (final var dir : this.directions) {
            final var bVisiting = TripletIntegerUtility.add(dir, b);
            if (this.grid.containsKey(bVisiting) && !this.neighborsList.contains(this.grid.get(bVisiting))) {
                // If the bubble is present in the grid but isn't already visited
                checkForUnconnectedBubblesRecursive(bVisiting);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeBubblesCascading(final Position p) {
        this.removeBubble(p);
        this.removeUnconnectedBubbles();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUnconnectedBubbles() {
        final var coll = this.checkForUnconnectedBubbles();
        this.grid.entrySet().removeIf(e -> coll.contains(e.getValue()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Bubble> getSameColorNeighbors(final Bubble b) {
        this.neighborsList.clear();
        final var bConv = this.convertCoords(b.getCoords());
        if (!this.grid.isEmpty() && this.grid.containsKey(bConv)) {
            this.getSameColorNeighborsRecursive(bConv);
        }

        return List.copyOf(this.neighborsList);
    }

    /**
     * This function is the recursive method that fills neighborsList with all the
     * neighbors of b.
     * 
     * @param b the bubble from where to start the search
     */
    private void getSameColorNeighborsRecursive(final Triplet<Integer, Integer, Integer> t) {
        if (this.grid.containsKey(t) && !this.neighborsList.contains(this.grid.get(t))) {
            // We have visited the bubble
            this.neighborsList.add(new BubbleImpl(this.grid.get(t)));

            for (final var dir : this.directions) {
                final var tripletNeighbor = TripletIntegerUtility.add(t, dir);
                if (this.grid.containsKey(tripletNeighbor)
                        && this.grid.get(tripletNeighbor).getColor().equals(this.grid.get(t).getColor())) {

                    // We visit the neighbor bubbles recursively
                    this.getSameColorNeighborsRecursive(tripletNeighbor);
                }
            }
        }
    }

    /**
     * @param p the 2D position to convert.
     * @return Triplet, the 3D converted position
     */
    private Triplet<Integer, Integer, Integer> convertCoords(final Position p) {

        final var q = Math.round((Math.sqrt(3.0) / 3.0 * p.getX() - 1.0 / 3.0 * p.getY()) / this.size);
        final var r = Math.round((2.0 / 3.0 * p.getY()) / this.size);
        return new TripletImpl<Integer, Integer, Integer>(Math.toIntExact(q), Math.toIntExact(r),
                Math.toIntExact(-q - r));
    }

    /**
     * @param triplet the 3D position to convert.
     * @return Position, the 2D converted position, the point in the middle of the
     *         bubble
     */
    private Position roundCoords(final Triplet<Integer, Integer, Integer> triplet) {
        final double x = this.size * (Math.sqrt(3.0) / 3.0 * triplet.getX() + Math.sqrt(3.0) / 2.0 * triplet.getY());
        final double y = this.size * (2.0 / 3.0 * triplet.getY());
        return new PositionImpl(x, y);
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
        return this.getBubbles().containsAll(other.getBubbles()) && other.getBubbles().containsAll(this.getBubbles())
                && this.info.equals(other.info);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BubblesGridImpl [grid=" + grid + "]";
    }

}
