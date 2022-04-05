package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BubblesGridTest {

    // Some test bubbles
    private final Bubble b1 = new BubbleImpl(new PositionImpl(0, 0), COLOR.RED);
    private final Bubble b2 = new BubbleImpl(new PositionImpl(1, 0), COLOR.ORANGE);
    private final Bubble b3 = new BubbleImpl(new PositionImpl(0, 1), COLOR.YELLOW);
    private final Bubble b4 = new BubbleImpl(new PositionImpl(1, 1), COLOR.GREEN);
    private final Bubble b5 = new BubbleImpl(new PositionImpl(3, 4), COLOR.BLUE);
    private final Bubble b6 = new BubbleImpl(new PositionImpl(4, 4), COLOR.PURPLE);

    @Test
    public void testBubblesGridPersistance() {
        final BubblesGrid g1 = new BubblesGridImpl();
        final BubblesGrid g2 = new BubblesGridImpl(List.of());
        final BubblesGrid g3 = new BubblesGridImpl(List.of(b1, b2, b3));
        // Two empty grids are equals
        assertEquals(g1, g2);
        assertFalse(g1.equals(g3));
        final var coll = g3.getBubbles();
        assertFalse(coll.isEmpty());
        assertTrue(coll.containsAll(List.of(b1, b2, b3, b6)));
        assertEquals(coll, g3.getBubbles());
        // Two grids with the same collection of bubbles are equals
        final BubblesGrid g4 = new BubblesGridImpl(coll);
        assertEquals(g3, g4);
        // Changes in the collection don't impact the grid, unless via grid methods
        coll.add(b4);
        assertFalse(coll.equals(g3.getBubbles()));
        assertFalse(coll.equals(g4.getBubbles()));
        assertEquals(g1.toString(), "BubblesGridImpl [grid=[]]");
        // TODO: toString in grid with bubbles, after Position gets implemented
    }

    @Test
    public void testBubblesGridAdd() {
        final BubblesGrid g1 = new BubblesGridImpl();
        assertEquals(g1.getBubbles().size(), 0);
        g1.addBubble(b1);
        assertEquals(g1.getBubbles().size(), 1);
        g1.addBubble(b2);
        g1.addBubble(b3);
        assertEquals(g1.getBubbles().size(), 3);
        // A bubble already inside the grid isn't added
        g1.addBubble(b1);
        g1.addBubble(b4);
        assertEquals(g1.getBubbles().size(), 4);

        final var list = new ArrayList<Bubble>();
        list.add(b1);
        list.add(b2);
        final BubblesGrid g2 = new BubblesGridImpl(list);
        assertEquals(g2.getBubbles().size(), 2);
        // Changes in the collection don't impact the grid, unless via grid methods
        list.add(b3);
        assertEquals(g2.getBubbles().size(), 2);
    }

    @Test
    public void testBubblesGridRemove() {
        final BubblesGrid g1 = new BubblesGridImpl(List.of(b1, b2, b3, b4));
        assertEquals(g1.getBubbles().size(), 4);
        g1.removeBubble(new PositionImpl(4, 4));
        // This bubble wasn't present in the grid, so no changes have happened
        assertEquals(g1.getBubbles().size(), 4);
        g1.removeBubble(b1.getCoords());
        assertEquals(g1.getBubbles().size(), 3);
        g1.removeBubble(b2.getCoords());
        g1.removeBubble(b3.getCoords());
        g1.removeBubble(b4.getCoords());
        assertEquals(g1.getBubbles().size(), 0);
        // The grid is empty

        final var list = new ArrayList<Bubble>();
        list.add(b1);
        list.add(b2);
        final BubblesGrid g2 = new BubblesGridImpl(list);
        assertEquals(g2.getBubbles().size(), 2);
        // Changes in the collection don't impact the grid, unless via grid methods
        list.remove(b3);
        assertEquals(g2.getBubbles().size(), 2);
    }

    @Test
    public void testBubblesGridLastRowY() {
        final BubblesGrid g1 = new BubblesGridImpl(List.of(b1, b3, b5));
        assertEquals(g1.getLastRowY(), b5.getCoords().getY());
        g1.removeBubble(b3.getCoords());
        assertEquals(g1.getLastRowY(), b5.getCoords().getY());
        g1.removeBubble(b5.getCoords());
        assertEquals(g1.getLastRowY(), b1.getCoords().getY());
        g1.removeBubble(b1.getCoords());
        assertEquals(g1.getLastRowY(), 0);
    }

    @Test
    public void testBubblesSameColorNeighbors() {
        final Bubble b7 = new BubbleImpl(new PositionImpl(1, 0), COLOR.RED);
        final Bubble b8 = new BubbleImpl(new PositionImpl(2, 0), COLOR.RED);
        final BubblesGrid g1 = new BubblesGridImpl(List.of());
        assertEquals(g1.getSameColorNeighbors(b1), List.of());
        assertEquals(g1.getBubbles().size(), 0);
        final BubblesGrid g2 = new BubblesGridImpl(List.of(b1, b2, b7, b8));
        assertEquals(g2.getSameColorNeighbors(b1), List.of(b1, b7, b8));
        assertEquals(g2.getSameColorNeighbors(b2), List.of(b2));
        g2.removeBubble(b7.getCoords());
        assertEquals(g2.getSameColorNeighbors(b1), List.of(b1));
    }
}
