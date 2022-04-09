package bbblast.model;

import java.util.Objects;

import bbblast.utils.Position;
import bbblast.utils.PositionImpl;

/**
 * The class that implements the bubble interface, used to rappresent a 2D
 * bubble.
 */
public class BubbleImpl implements Bubble {

    private final PositionImpl pos;
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
        this.pos = new PositionImpl(p.getX(), p.getY());
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
        return new PositionImpl(this.pos.getX(), this.pos.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Bubble " + this.color.toString() + ", " + this.pos.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(color);
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
        final BubbleImpl other = (BubbleImpl) obj;
        return color == other.color && pos.equals(other.pos);
    }

}
