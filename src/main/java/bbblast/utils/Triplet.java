package bbblast.utils;

/**
 * @param <X> the type of the first element of the triplet.
 * @param <Y> the type of the second element of the triplet.
 * @param <Z> the type of the third element of the triplet. This class
 *            represents a triplet of three different elements.
 */
public interface Triplet<X, Y, Z> {

    /**
     * @return the first element of the triplet.
     */
    X getX();

    /**
     * @return the second element of the triplet.
     */
    Y getY();

    /**
     * @return the third element of the triplet.
     */
    Z getZ();

}
