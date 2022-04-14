package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import bbblast.utils.Position;
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
        assertEquals(g1, g2, "Two empty grids are equals");
        assertFalse(g1.equals(g3), "The grids contain different bubbles");
        var coll = g3.getBubbles();
        assertFalse(coll.isEmpty(), "The returned collection has bubbles");
        assertTrue(coll.containsAll(List.of(b1, b2, b3, b4)), "The returned collection has the bubbles of the grid");
        assertEquals(coll, g3.getBubbles(), "The collections contain the same bubbles");
        final BubblesGrid g4 = new BubblesGridImpl(coll);
        assertEquals(g3, g4, "Two grids with the same collection of bubbles are equals");
        coll = new ArrayList<Bubble>(coll);
        coll.add(b6);
        assertFalse(coll.equals(g3.getBubbles()),
                "Changes in the collection don't impact the grid, unless via grid methods");
        assertFalse(coll.equals(g4.getBubbles()),
                "Changes in the collection don't impact the grid, unless via grid methods");
    }

    @Test
    public void testBubblesGridtoString() {
        final BubblesGrid g1 = new BubblesGridImpl();
        assertEquals(g1.toString(), "BubblesGridImpl [grid={}]", "The representation of an empty grid");
        g1.addBubble(b1);
        assertEquals(g1.toString(), "BubblesGridImpl [grid={TripletImpl [x=0, y=0, z=0]=" + b1.toString() + "}]",
                "The representation of a bubble");
        g1.removeBubble(b1.getCoords());
        assertEquals(g1.toString(), "BubblesGridImpl [grid={}]");
        g1.addBubble(b2);
        assertEquals(g1.toString(), "BubblesGridImpl [grid={TripletImpl [x=1, y=0, z=-1]=" + b2.toString() + "}]",
                "The representation of a bubble");
        g1.addBubble(b3);
        g1.removeBubble(b2.getCoords());
        assertEquals(g1.toString(), "BubblesGridImpl [grid={TripletImpl [x=0, y=1, z=-1]=" + b3.toString() + "}]",
                "The representation of a bubble");

    }

    @Test
    public void testBubblesGridAdd() {
        final BubblesGrid g1 = new BubblesGridImpl();
        assertEquals(g1.getBubbles().size(), 0, "An empty grid has no bubbles");
        g1.addBubble(b1);
        assertEquals(g1.getBubbles().size(), 1, "The grid has only a bubble");
        g1.addBubble(b2);
        g1.addBubble(b3);
        assertEquals(g1.getBubbles().size(), 3, "The bubbles contained by the grid");
        g1.addBubble(b1);
        g1.addBubble(b4);
        assertEquals(g1.getBubbles().size(), 4, "A bubble already inside the grid isn't added");

        final var list = new ArrayList<Bubble>();
        list.add(b1);
        list.add(b2);
        final BubblesGrid g2 = new BubblesGridImpl(list);
        assertEquals(g2.getBubbles().size(), 2, "The original grid");
        list.add(b3);
        assertEquals(g2.getBubbles().size(), 2,
                "Changes in the collection don't impact the grid, unless via grid methods");
    }

    @Test
    public void testBubblesGridRemove() {
        final BubblesGrid g1 = new BubblesGridImpl(List.of(b1, b2, b3, b4));
        assertEquals(g1.getBubbles().size(), 4, "The original grid");
        g1.removeBubble(new PositionImpl(4, 4));
        assertEquals(g1.getBubbles().size(), 4, "This bubble wasn't present in the grid, so no changes have happened");
        g1.removeBubble(b1.getCoords());
        assertEquals(g1.getBubbles().size(), 3, "Removing a bubble decreases the bubbles in the grid");
        g1.removeBubble(b2.getCoords());
        g1.removeBubble(b3.getCoords());
        g1.removeBubble(b4.getCoords());
        assertEquals(g1.getBubbles().size(), 0, "The grid is empty");

        final var list = new ArrayList<Bubble>();
        list.add(b1);
        list.add(b2);
        final BubblesGrid g2 = new BubblesGridImpl(list);
        assertEquals(g2.getBubbles().size(), 2, "The original grid");
        list.remove(b3);
        assertEquals(g2.getBubbles().size(), 2,
                "Changes in the collection don't impact the grid, unless via grid methods");
    }

    @Test
    public void testBubblesGridLastRowY() {
        final BubblesGrid g1 = new BubblesGridImpl(List.of(b1, b3, b5));
        assertEquals(g1.getLastRowY(), b5.getCoords().getY(), "The lowest bubble");
        g1.removeBubble(b3.getCoords());
        assertEquals(g1.getLastRowY(), b5.getCoords().getY(), "Still the lowest bubble");
        g1.removeBubble(b5.getCoords());
        assertEquals(g1.getLastRowY(), b1.getCoords().getY(), "New lowest bubble");
        g1.removeBubble(b1.getCoords());
        assertEquals(g1.getLastRowY(), 0, "No bubbles are present, so the lowest is 0");
    }

    @Test
    public void testBubblesSameColorNeighbors() {
        final Bubble b7 = new BubbleImpl(new PositionImpl(0, 1), COLOR.RED);
        final Bubble b8 = new BubbleImpl(new PositionImpl(2, 0), COLOR.RED);
        final Bubble b9 = new BubbleImpl(new PositionImpl(1, 1), COLOR.RED);
        final BubblesGrid g1 = new BubblesGridImpl();
        assertTrue(g1.getSameColorNeighbors(b1).containsAll(List.of()),
                "No neighboring bubbles are present in an empty grid");
        assertEquals(g1.getBubbles().size(), 0, "An empty grid contains no bubbles");
        final BubblesGrid g2 = new BubblesGridImpl(List.of(b1, b2, b7, b8));
        assertTrue(g2.getSameColorNeighbors(b1).containsAll(List.of(b1, b7)), "The red neighbors");
        assertTrue(g2.getSameColorNeighbors(b2).containsAll(List.of(b2)), "The orange neighbors");
        g2.addBubble(b9);
        assertTrue(g2.getSameColorNeighbors(b1).containsAll(List.of(b1, b7, b8, b9)),
                "Adding b9 connects b8 to the neighborhood of b1");
        assertTrue(g2.getSameColorNeighbors(b2).containsAll(List.of(b2)), "The orange neighbors remain the same");
        g2.removeBubble(b7.getCoords());
        assertTrue(g2.getSameColorNeighbors(b1).containsAll(List.of(b1)), "Removing b7 divides the red bubbles");
        assertTrue(g2.getSameColorNeighbors(b8).containsAll(List.of(b8, b9)),
                "Still neighbors after the removal of b7");
    }

    @Test
    public void testIsBubbleAttachable() {
        final BubblesGrid g1 = new BubblesGridImpl();
        assertTrue(g1.isBubbleAttachable(b1), "This bubble connects to the top of the grid");
        assertFalse(g1.isBubbleAttachable(b4), "This bubble can't be connected");
        g1.addBubble(b1);
        assertTrue(g1.isBubbleAttachable(b2), "This bubble connects to an existing bubble");
        assertFalse(g1.isBubbleAttachable(b4), "This bubble still can't be connected");
        g1.addBubble(b2);
        assertTrue(g1.isBubbleAttachable(b4), "This bubble connects to an existing bubble");
        g1.addBubble(b4);

    }

    @Test
    public void testCheckForUnconnectedBubbles() {
        final BubblesGrid g1 = new BubblesGridImpl();
        assertTrue(g1.checkForUnconnectedBubbles().isEmpty(), "An empty grid has no unconnected bubbles");
        final BubblesGrid g2 = new BubblesGridImpl(List.of(b1, b2, b3, b4));
        assertTrue(g2.checkForUnconnectedBubbles().isEmpty(), "All bubbles are connected");
        final BubblesGrid g3 = new BubblesGridImpl(List.of(b1, b2, b3, b4, b5));
        assertFalse(g3.checkForUnconnectedBubbles().isEmpty(), "Some bubbles are unconnected");
        assertTrue(g3.checkForUnconnectedBubbles().containsAll(List.of(b5)), "This bubble is unconnected");
        final Bubble bb = new BubbleImpl(new PositionImpl(7, 7), COLOR.GREEN);
        final BubblesGrid g4 = new BubblesGridImpl(List.of(b1, b2, b3, b4, b5, b6, bb));
        assertFalse(g4.checkForUnconnectedBubbles().isEmpty(), "Some bubbles are unconnected");
        assertTrue(g4.checkForUnconnectedBubbles().containsAll(List.of(b5, b6, bb)), "These bubbles are unconnected");
        g4.removeBubble(b1.getCoords());
        g4.removeBubble(b2.getCoords());
        g4.removeBubble(b3.getCoords());
        g4.removeBubble(b4.getCoords());
        assertTrue(g1.checkForUnconnectedBubbles().isEmpty(), "All bubbles are unconnectes");
        assertTrue(g1.checkForUnconnectedBubbles().containsAll(g1.getBubbles()), "All bubbles are unconnected");

    }

    @Test
    public void testMoveBubblesDown() {
        final BubblesGridImpl g1 = new BubblesGridImpl();
        assertTrue(g1.getBubbles().isEmpty(), "An empty grid");
        g1.moveBubblesDown(1);
        assertTrue(g1.getBubbles().isEmpty(), "No changes by moving down no bubbles");
        final BubblesGrid g2 = new BubblesGridImpl(List.of(b1, b2, b3, b4));
        g2.moveBubblesDown(0);
        assertEquals(g2, new BubblesGridImpl(List.of(b1, b2, b3, b4)), "Moving down by 0 rows doesn't change the grid");
        g2.moveBubblesDown(-4);
        assertEquals(g2, new BubblesGridImpl(List.of(b1, b2, b3, b4)),
                "Moving down by a negative number doesn't change the grid");
        g2.moveBubblesDown(2);
        final Bubble b1t = new BubbleImpl(b1);
        final Bubble b2t = new BubbleImpl(b2);
        final Bubble b3t = new BubbleImpl(b3);
        final Bubble b4t = new BubbleImpl(b4);
        final Position p = new PositionImpl(0, 2);
        b1t.moveBy(p);
        b2t.moveBy(p);
        b3t.moveBy(p);
        b4t.moveBy(p);
        assertEquals(g2, new BubblesGridImpl(List.of(b1t, b2t, b3t, b4t)),
                "The grid is the same as moving individually all the bubbles");

    }

    @Test
    public void testRemoveBubblesCascading() {
        final BubblesGridImpl g1 = new BubblesGridImpl();
        final var origGrid = g1.getBubbles();
        g1.removeBubblesCascading(b1.getCoords());
        final var finalGrid = g1.getBubbles();
        assertTrue(g1.checkForUnconnectedBubbles().isEmpty(), "An empty Grid has no unconnected bubbles");
        assertTrue(finalGrid.containsAll(origGrid), "An empty Grid has no unconnected bubbles");
        final BubblesGridImpl g2 = new BubblesGridImpl(List.of(b1, b2, b3, b4));
        g2.removeBubblesCascading(b2.getCoords());
        assertTrue(g2.checkForUnconnectedBubbles().isEmpty(), "All bubbles are connected");
        assertTrue(g2.getBubbles().containsAll(List.of(b1, b3, b4)), "This grid has all connected bubbles");
        assertTrue(g2.checkForUnconnectedBubbles().isEmpty(),
                "All bubbles are connected, even after the removal of some");

        final BubblesGridImpl g3 = new BubblesGridImpl(List.of(b1, b2, b3, b4, b5));
        assertFalse(g3.checkForUnconnectedBubbles().isEmpty(), "Some bubbles are unconnected");
        g3.removeBubblesCascading(b3.getCoords());
        assertTrue(g3.getBubbles().containsAll(List.of(b1, b2, b4)), "This grid has some unconnected bubbles");
        assertTrue(g3.checkForUnconnectedBubbles().isEmpty(), "All unconnected bubbles were removed");
        final BubblesGridImpl g4 = new BubblesGridImpl(List.of(b1, b2, b3, b4));
        assertTrue(g4.checkForUnconnectedBubbles().isEmpty(), "All bubbles are connected");
        g4.moveBubblesDown(1);
        assertFalse(g4.checkForUnconnectedBubbles().isEmpty(),
                "All bubbles are unconnected, after moving down the whole grid");
        g4.removeBubblesCascading(b1.getCoords());
        assertTrue(g4.getBubbles().isEmpty(), "All the bubbles were removed");

    }

    @Test
    public void testRemoveUnconnectedBubbles() {
        final BubblesGridImpl g1 = new BubblesGridImpl();
        var origGrid = g1.getBubbles();
        g1.removeUnconnectedBubbles();
        var finalGrid = g1.getBubbles();
        assertTrue(finalGrid.containsAll(origGrid), "An empty grid has no unconnected bubbles");
        final BubblesGridImpl g2 = new BubblesGridImpl(List.of(b1, b2, b3, b4));
        origGrid = g2.getBubbles();
        g2.removeUnconnectedBubbles();
        finalGrid = g2.getBubbles();
        assertTrue(finalGrid.containsAll(origGrid), "This grid has all connected bubbles");
        final BubblesGridImpl g3 = new BubblesGridImpl(List.of(b1, b2, b3, b4, b5));
        origGrid = g3.getBubbles();
        g3.removeUnconnectedBubbles();
        finalGrid = g3.getBubbles();
        assertFalse(finalGrid.containsAll(origGrid), "This grid had some unconnected bubbles");
        assertFalse(finalGrid.contains(b5));
        final BubblesGridImpl g4 = new BubblesGridImpl(List.of(b1, b2, b3, b4));
        g4.moveBubblesDown(1);
        origGrid = g4.getBubbles();
        g4.removeUnconnectedBubbles();
        finalGrid = g4.getBubbles();
        assertFalse(finalGrid.containsAll(origGrid), "After moving down the grid, all the bubbles are unconnected");
        assertTrue(finalGrid.isEmpty(), "The grid was full of unconnected bubbles, so now it's empty");

    }
}
