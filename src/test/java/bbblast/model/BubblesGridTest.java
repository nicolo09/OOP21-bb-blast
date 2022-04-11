package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import bbblast.utils.PositionImpl;

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
        final BubblesGrid g3 = new BubblesGridImpl(List.of(b1, b2, b3, b4));
        // Two empty grids are equals
        assertEquals(g1, g2);
        assertFalse(g1.equals(g3));
        var coll = g3.getBubbles();
        assertFalse(coll.isEmpty());
        assertTrue(coll.containsAll(List.of(b1, b2, b3, b4)));
        assertEquals(coll, g3.getBubbles());
        // Two grids with the same collection of bubbles are equals
        final BubblesGrid g4 = new BubblesGridImpl(coll);
        assertEquals(g3, g4);
        // Changes in the collection don't impact the grid, unless via grid methods
        coll = new ArrayList<Bubble>(coll);
        coll.add(b6);
        assertFalse(coll.equals(g3.getBubbles()));
        assertFalse(coll.equals(g4.getBubbles()));
    }

    @Test
    public void testBubblesGridtoString() {
        final BubblesGrid g1 = new BubblesGridImpl();
        assertEquals(g1.toString(), "BubblesGridImpl [grid={}]");
        g1.addBubble(b1);
        assertEquals(g1.toString(), "BubblesGridImpl [grid={TripletImpl [x=0, y=0, z=0]=" + b1.toString() + "}]");
        g1.removeBubble(b1.getCoords());
        assertEquals(g1.toString(), "BubblesGridImpl [grid={}]");
        g1.addBubble(b2);
        assertEquals(g1.toString(), "BubblesGridImpl [grid={TripletImpl [x=1, y=0, z=-1]=" + b2.toString() + "}]");
        g1.addBubble(b3);
        g1.removeBubble(b2.getCoords());
        assertEquals(g1.toString(), "BubblesGridImpl [grid={TripletImpl [x=0, y=1, z=-1]=" + b3.toString() + "}]");

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
        final Bubble b7 = new BubbleImpl(new PositionImpl(0, 1), COLOR.RED);
        final Bubble b8 = new BubbleImpl(new PositionImpl(2, 0), COLOR.RED);
        final Bubble b9 = new BubbleImpl(new PositionImpl(1, 1), COLOR.RED);
        final BubblesGrid g1 = new BubblesGridImpl(List.of());
        assertTrue(g1.getSameColorNeighbors(b1).containsAll(List.of()));
        assertEquals(g1.getBubbles().size(), 0);
        final BubblesGrid g2 = new BubblesGridImpl(List.of(b1, b2, b7, b8));
        assertTrue(g2.getSameColorNeighbors(b1).containsAll(List.of(b1, b7)));
        assertTrue(g2.getSameColorNeighbors(b2).containsAll(List.of(b2)));
        // Adding b9 connects b8 to the neighborhood of b1
        g2.addBubble(b9);
        assertTrue(g2.getSameColorNeighbors(b1).containsAll(List.of(b1, b7, b8, b9)));
        assertTrue(g2.getSameColorNeighbors(b2).containsAll(List.of(b2)));
        g2.removeBubble(b7.getCoords());
        assertTrue(g2.getSameColorNeighbors(b1).containsAll(List.of(b1)));
        assertTrue(g2.getSameColorNeighbors(b8).containsAll(List.of(b8, b9)));
    }

    @Test
    public void testIsBubbleAttachable() {
        final BubblesGrid g1 = new BubblesGridImpl();
        assertTrue(g1.isBubbleAttachable(b1));
        assertFalse(g1.isBubbleAttachable(b4));
        g1.addBubble(b1);
        assertTrue(g1.isBubbleAttachable(b2));
        assertFalse(g1.isBubbleAttachable(b4));
        g1.addBubble(b2);
        assertTrue(g1.isBubbleAttachable(b4));
        g1.addBubble(b4);

    }

    @Test
    public void testMoveBubblesDown() {
        final BubblesGridImpl g1 = new BubblesGridImpl();
        assertTrue(g1.getBubbles().isEmpty());
        g1.moveBubblesDown(1);
        assertTrue(g1.getBubbles().isEmpty());
        final BubblesGrid g2 = new BubblesGridImpl(List.of(b1, b2, b3, b4));
        g2.moveBubblesDown(0);
        assertEquals(g2, new BubblesGridImpl(List.of(b1, b2, b3, b4)));
        g2.moveBubblesDown(2);
        final Bubble b1t = new BubbleImpl(b1);
        final Bubble b2t = new BubbleImpl(b2);
        final Bubble b3t = new BubbleImpl(b3);
        final Bubble b4t = new BubbleImpl(b4);
        b1t.moveBy(new PositionImpl(0, 2));
        b2t.moveBy(new PositionImpl(0, 2));
        b3t.moveBy(new PositionImpl(0, 2));
        b4t.moveBy(new PositionImpl(0, 2));

        assertEquals(g2, new BubblesGridImpl(List.of(b1t, b2t, b3t, b4t)));

    }
}
