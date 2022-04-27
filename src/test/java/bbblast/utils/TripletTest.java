package bbblast.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


/***
 * 
 * This class tests methods of {@link Triplet}.
 */
public class TripletTest {

    /***
     * 
     * This test checks equals for {@link Triplet}.
     */
    @Test
    public void testTripletEquals() {
        final Triplet<Integer, Integer, Integer> t1 = new TripletImpl<>(0, 0, 0);
        final Triplet<Integer, Integer, Integer> t2 = new TripletImpl<>(1, 1, 1);
        final Triplet<Integer, Integer, Integer> t3 = new TripletImpl<>(t2);
        assertFalse(t1.equals(t2), "A triplet created from the same object via constructor is equals");
        assertEquals(t1, new TripletImpl<Integer, Integer, Integer>(0, 0, 0),
                "A triplet with the same parameters is equals");
        assertEquals(t2, t3, "Two triplets with the same parameters are equals");
    }

    /***
     * 
     * This test checks constructor for {@link Triplet}.
     */
    @Test
    public void testTripletPersistence() {
        int a = 1;
        int b = 2;
        int c = 3;
        final Triplet<Integer, Integer, Integer> t1 = new TripletImpl<>(a, b, c);
        assertTrue(t1.getX().equals(a), "The parameter of the triplet and the original value are equals");
        assertTrue(t1.getY().equals(b), "The parameter of the triplet and the original value are equals");
        assertTrue(t1.getZ().equals(c), "The parameter of the triplet and the original value are equals");
        assertEquals(t1.toString(), "TripletImpl [x=" + a + ", y=" + b + ", z=" + c + "]",
                "The triplet is printed this way");
        a++;
        b--;
        c = c + 3;
        assertFalse(t1.getX().equals(a), "Changes to local values don't impact the triplet value");
        assertFalse(t1.getY().equals(b), "Changes to local values don't impact the triplet value");
        assertFalse(t1.getZ().equals(c), "Changes to local values don't impact the triplet value");
        assertEquals(t1.toString(), "TripletImpl [x=1, y=2, z=3]", "The triplet retains the correct values");

    }
}
