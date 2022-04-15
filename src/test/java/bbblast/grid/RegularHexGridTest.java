package bbblast.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bbblast.model.GridInfo;
import bbblast.model.RegularHexGridInfo;

public class RegularHexGridTest {

    @Test
    public void testRegularHexGrid() {
        int bubbleWidth = 10;
        int bubbleHeight = 20;
        int ratio = 50;
        final GridInfo grid = new RegularHexGridInfo(bubbleWidth, bubbleHeight, ratio);
        assertEquals(grid.getPointsWidth(), bubbleWidth * ratio, "Wrong points width");
        // Si, quel double serve perché in Java le divisioni fra interi fanno un intero
        assertEquals(grid.getPointsHeight(), (double) 3 / 4 * (2 * ((50 * Math.sqrt(3)) / 3) * (bubbleHeight - 1))
                + 2 * ((ratio * Math.sqrt(3)) / 3));
    }
}
