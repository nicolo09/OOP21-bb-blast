package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import bbblast.utils.Position;
import bbblast.utils.PositionImpl;

public class BubbleTest {

    @Test
    public void testBubbleEquals() {
        final Bubble b1 = new BubbleImpl(new PositionImpl(0, 0), COLOR.BLUE);
        final Bubble b2 = new BubbleImpl(new PositionImpl(1, 2), COLOR.RED);
        final Bubble b3 = new BubbleImpl(new PositionImpl(-1, 5), COLOR.BLUE);
        final Bubble b4 = new BubbleImpl(new PositionImpl(1, 2), COLOR.GREEN);
        assertFalse(b1.equals(b2), "Two bubbles with different colors are not equals");
        assertFalse(b1.equals(b3), "Two bubbles with different positions are not equals");
        assertFalse(b2.equals(b4), "Two bubbles with different positions are not equals");
        assertEquals(b1, new BubbleImpl(b1), "Two bubbles with same positions and color are equals");
    }

    @Test
    public void testBubblePersistence() {

        Position p = new PositionImpl(3, 4);
        final Bubble b = new BubbleImpl(p, COLOR.YELLOW);
        assertEquals(b.getCoords(), p, "The two positions are equals");
        assertEquals(b.toString(), "Bubble " + b.getColor().toString() + ", " + b.getCoords().toString(),
                "The bubble is printed this way");
        p = new PositionImpl(4, 3);
        assertFalse(b.getCoords().equals(p), "Changes to the position don't impact the bubble");
        assertEquals(b.toString(), "Bubble " + b.getColor().toString() + ", " + new PositionImpl(3, 4).toString(),
                "The bubble retains the correct value");
    }

    @Test
    public void testBubbleMoveBy() {
        Position p = new PositionImpl(1, 2);
        final Bubble b = new BubbleImpl(p, COLOR.PURPLE);
        assertEquals(b.getCoords(), p, "The two positions are equals");
        p.translate(2, 2);
        assertFalse(b.getCoords().equals(p), "Bubble can only be modified by Bubble methods");
        p = b.getCoords();
        p.translate(3, 3);
        assertEquals(p, new PositionImpl(1 + 3, 2 + 3), "The new position is the sum of the components");
        assertFalse(p.equals(b.getCoords()), "Bubble can only be modified by Bubble methods");
        b.moveBy(new PositionImpl(4, 4));
        assertEquals(b.getCoords(), new PositionImpl(5, 6), "The same position");

    }

}
