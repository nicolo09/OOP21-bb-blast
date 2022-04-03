package bbblast.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TripletTest {

    @Test
    public void testTripletEquals() {
        final Triplet<Integer, Integer, Integer> t1 = new TripletImpl<>(0, 0, 0);
        final Triplet<Integer, Integer, Integer> t2 = new TripletImpl<>(1, 1, 1);
        final Triplet<Integer, Integer, Integer> t3 = new TripletImpl<>(t2);
        assertFalse(t1.equals(t2));
        assertEquals(t1, new TripletImpl<Integer, Integer, Integer>(0, 0, 0));
        assertEquals(t2, t3);
    }

    @Test
    public void testTripletPersistence() {
        int a = 1;
        int b = 2;
        int c = 3;
        final Triplet<Integer, Integer, Integer> t1 = new TripletImpl<>(a, b, c);
        assertTrue(t1.getX().equals(a));
        assertTrue(t1.getY().equals(b));
        assertTrue(t1.getZ().equals(c));
        assertEquals(t1.toString(), "TripletImpl [x=" + a + ", y=" + b + ", z=" + c + "]");
        a++;
        b--;
        c = c + 3;
        assertFalse(t1.getX().equals(a));
        assertFalse(t1.getY().equals(b));
        assertFalse(t1.getZ().equals(c));
        assertEquals(t1.toString(), "TripletImpl [x=1, y=2, z=3]");

    }
}
