package bbblast.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/***
 * 
 * This class tests methods of {@link VectorConverter}.
 */
public class VectorConverterTest {

    private static final double DELTA = 0.0001;

    /***
     * 
     * This test checks equals, setAngle, setModule, getComponents for
     * {@link VectorConverter}.
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void testVectorConverterCorrectness() {
        final VectorConverter vc = new VectorConverterImpl(1);
        assertEquals(new PositionImpl(0, 0), vc.getComponents(), "No module set");
        vc.setModule(10);
        var tmp = new PositionImpl(10, 0);
        assertEquals(tmp.getX(), vc.getComponents().getX(), DELTA, "Angle=0");
        assertEquals(tmp.getY(), vc.getComponents().getY(), DELTA, "Angle=0");
        vc.setAngle(45);
        assertEquals(vc.getComponents().getX(), -vc.getComponents().getY(), DELTA, "Angle=45");
        vc.setAngle(180);
        tmp = new PositionImpl(-10, 0);
        assertEquals(tmp.getX(), vc.getComponents().getX(), DELTA, "Angle=180");
        assertEquals(tmp.getY(), vc.getComponents().getY(), DELTA, "Angle=180");
    }

}
