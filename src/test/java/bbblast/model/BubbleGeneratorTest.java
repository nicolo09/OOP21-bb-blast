package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import bbblast.utils.PositionImpl;
/**
 * This class tests methods of {@link BubbleGenerator}.
 */
public class BubbleGeneratorTest {
    private final BubbleGeneratorImpl b1 = new BubbleGeneratorImpl(List.of(COLOR.GREEN,
                                                             COLOR.BLUE, 
                                                             COLOR.ORANGE, 
                                                             COLOR.PURPLE, 
                                                             COLOR.RED, 
                                                             COLOR.YELLOW));
    /**
     * This test check if the bubble has been created in the first coordination.
     */
    @Test
    public void bubbleCreationTest() {
        assertNotEquals(b1.generate(new PositionImpl(0, 0)), null, "They are different");
    }
    /**
     * This test check if the 2 bubbles has been created in 2 different positions.
     */
    @Test
    public void bubblePositionTest() {
        assertEquals(b1.generate(new PositionImpl(1, 2)).getCoords(), new PositionImpl(1, 2), "They are the same");
    }

}
