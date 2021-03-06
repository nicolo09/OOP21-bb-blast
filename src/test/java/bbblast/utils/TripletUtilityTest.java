package bbblast.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/***
 * 
 * This class tests methods of {@link TripletIntegerUtility}.
 */
public class TripletUtilityTest {

    /***
     * 
     * This test checks add for {@link TripletIntegerUtility}.
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void testTripletSum() {
        final Triplet<Integer, Integer, Integer> t1 = new TripletImpl<>(1, 2, 3);
        final Triplet<Integer, Integer, Integer> t2 = new TripletImpl<>(-1, -2, -3);
        final Triplet<Integer, Integer, Integer> t3 = new TripletImpl<>(3, 2, 0);
        final Triplet<Integer, Integer, Integer> t4 = new TripletImpl<>(-3, 0, -2);

        final Triplet<Integer, Integer, Integer> t5 = TripletIntegerUtility.add(t1, t2);
        final Triplet<Integer, Integer, Integer> t6 = TripletIntegerUtility.add(t1, t3);
        final Triplet<Integer, Integer, Integer> t7 = TripletIntegerUtility.add(t2, t4);
        final Triplet<Integer, Integer, Integer> t8 = TripletIntegerUtility.add(t2, t3);
        assertEquals(t5, new TripletImpl<>(0, 0, 0), "The result is the sum of the triplet components");
        assertEquals(t6, new TripletImpl<>(4, 4, 3), "The result is the sum of the components");
        assertEquals(t7, new TripletImpl<>(-4, -2, -5), "The sum of the components");
        assertEquals(t8, new TripletImpl<>(2, 0, -3), "The sum of the triplet components");

    }
}
