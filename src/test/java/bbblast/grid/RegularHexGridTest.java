package bbblast.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bbblast.model.GridInfo;
import bbblast.model.RegularHexGridInfo;
/**
 * Tests for the {@link RegularHexGridInfo}.
 */
public class RegularHexGridTest {

    /**
     * tests {@link RegularHexGridInfo} method's return values.
     */
    @Test
    public void testRegularHexGrid() {
        final int bubbleWidth = 10;
        final int bubbleHeight = 20;
        final int ratio = 50;
        final GridInfo grid = new RegularHexGridInfo(bubbleWidth, bubbleHeight, ratio);
        assertEquals(grid.getPointsWidth(), bubbleWidth * ratio + ratio/2, "Wrong points width");
        // Si, quel double serve perché in Java le divisioni fra interi fanno un intero
        assertEquals(grid.getPointsHeight(), (double) 3 / 4 * (2 * ((ratio * Math.sqrt(3)) / 3) * (bubbleHeight - 1))
                + 2 * ((ratio * Math.sqrt(3)) / 3));
    }
}
