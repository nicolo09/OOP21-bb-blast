package bbblast.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class VectorConverterTest {

    @Test
    public void testVectorConverterCorrectness() {
        final VectorConverter vc = new VectorConverterImpl(1);
        assertEquals(new PositionImpl(0, 0), vc.getComponents(), "No module set");
        vc.setModule(10);
        var tmp = new PositionImpl(10, 0);
        assertEquals(tmp.getX(), vc.getComponents().getX(), 0.001, "Angle=0");
        assertEquals(tmp.getY(), vc.getComponents().getY(), 0.001, "Angle=0");
        vc.setAngle(45);
        assertEquals(vc.getComponents().getX(), -vc.getComponents().getY(), 0.001, "Angle=45");
        vc.setAngle(180);
        tmp = new PositionImpl(-10, 0);
        assertEquals(tmp.getX(), vc.getComponents().getX(), 0.001, "Angle=180");
        assertEquals(tmp.getY(), vc.getComponents().getY(), 0.001, "Angle=180");
    }

}
