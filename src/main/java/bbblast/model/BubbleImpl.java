package bbblast.model;

/**
 * The class that implements the bubble interface, used to rappresent a 2D
 * bubble.
 */
public class BubbleImpl implements Bubble {

    private final Position pos;
    private final COLOR color;

    /**
     * @param b a bubble object. This constructor creates a new bubble with the same
     *          characteristics as b.
     */
    public BubbleImpl(final Bubble b) {
        this(b.getCoords(), b.getColor());
    }

    /**
     * @param p is the position of the new bubble
     * @param c is the COLOR of the new bubble This constructor creates a new bubble
     *          with the characteristics provided.
     */
    public BubbleImpl(final Position p, final COLOR c) {
        this.pos = new Position(p.getX(), p.getY());
        this.color = c;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public COLOR getColor() {
        return this.color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getCoords() {
        return new Position(this.pos.getX(), this.pos.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Bubble " + this.color.toString() + ", " + this.pos.toString();
    }

}
