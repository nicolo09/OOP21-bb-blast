package bbblast.utils;

import java.io.Serializable;
import java.util.Objects;

/**
 * @param <X> the type of the first element of the triplet.
 * @param <Y> the type of the second element of the triplet.
 * @param <Z> the type of the third element of the triplet. This class
 *            implements a triplet of three different elements.
 */
public final class TripletImpl<X, Y, Z> implements Triplet<X, Y, Z>, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4377376501856616611L;
    private final X x;
    private final Y y;
    private final Z z;

    /**
     * @param x the first element of the triplet.
     * @param y the second element of the triplet.
     * @param z the third element of the triplet. This constructor creates a new
     *          triplet with the values provided.
     */
    public TripletImpl(final X x, final Y y, final Z z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public TripletImpl(final Triplet<X, Y, Z> t) {
        this(t.getX(), t.getY(), t.getZ());
    }

    @Override
    public X getX() {
        return this.x;
    }

    @Override
    public Y getY() {
        return this.y;
    }

    @Override
    public Z getZ() {
        return this.z;
    }

    @Override
    public String toString() {
        return "TripletImpl [x=" + this.x + ", y=" + this.y + ", z=" + this.z + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

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
        @SuppressWarnings("unchecked")
        final TripletImpl<X, Y, Z> other = (TripletImpl<X, Y, Z>) obj;
        return Objects.equals(x, other.x) && Objects.equals(y, other.y) && Objects.equals(z, other.z);
    }

}
