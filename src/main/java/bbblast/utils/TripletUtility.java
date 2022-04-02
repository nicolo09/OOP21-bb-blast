package bbblast.utils;

/**
 * @param<X> the type of the first number in the triplet.
 * @param<Y> the type of the second number in the triplet.
 * @param<Z> the type of the third number in the triplet. This class is a static
 *               utility class for Triplet with Number parameters
 */
final public class TripletUtility<X extends Number, Y extends Number, Z extends Number> {

    private TripletUtility() {
        throw new UnsupportedOperationException("Cannot instantiate a static class");
    }

    /**
     * @param<X> the type of the first number in the triplet.
     * @param<Y> the type of the second number in the triplet.
     * @param<Z> the type of the third number in the triplet.
     * @param a the first triplet.
     * @param b the second triplet.
     * @return a new triplet with as elements the sum of the components of the two
     *         given triplets.
     * 
     */
    static public Triplet<X, Y, Z> add(final Triplet<X, Y, Z> a, final Triplet<X, Y, Z> b) {
        return new TripletImpl<>(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    }

}
