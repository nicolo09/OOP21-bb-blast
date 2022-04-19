package bbblast.utils;

/**
 * This class is a static utility class for Triplet with Integer parameters.
 */
public final class TripletIntegerUtility {

    private TripletIntegerUtility() {
        throw new UnsupportedOperationException("Cannot instantiate a static class");
    }

    /**
     * @param<Integer> The type of the numbers in the triplets.
     * @param a the first triplet.
     * @param b the second triplet.
     * @return a new triplet with as elements the sum of the components of the two
     *         given triplets.
     * 
     */
     public static Triplet<Integer, Integer, Integer> add(final Triplet<Integer, Integer, Integer> a, final Triplet<Integer, Integer, Integer> b) {
        return new TripletImpl<Integer, Integer, Integer>(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    }

}
