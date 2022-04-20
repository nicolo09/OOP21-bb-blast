package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import bbblast.utils.PositionImpl;

public class BubblesGridTest {

    // Some test bubbles
    private final Bubble b1 = new BubbleImpl(new PositionImpl(0, 0), COLOR.RED);
    private final Bubble b2 = new BubbleImpl(new PositionImpl(0.65, 0), COLOR.ORANGE);
    private final Bubble b3 = new BubbleImpl(new PositionImpl(0.49, 0.45), COLOR.YELLOW);
    private final Bubble b4 = new BubbleImpl(new PositionImpl(0.82, 0.45), COLOR.GREEN);
    private final Bubble b5 = new BubbleImpl(new PositionImpl(0.987, 1.4), COLOR.BLUE);
    private final Bubble b6 = new BubbleImpl(new PositionImpl(1.31, 1.4), COLOR.PURPLE);
    private final GridInfo gridInfo = new RegularHexGridInfo(5, 10, 1);

    @Test
    public void testBubblesGridPersistance() {
        final BubblesGrid g1 = new BubblesGridImpl(gridInfo);
        final BubblesGrid g2 = new BubblesGridImpl(List.of(), gridInfo);
        final BubblesGrid g3 = new BubblesGridImpl(List.of(b1, b2, b3, b4), gridInfo);
        assertEquals(g1, g2, "Two empty grids are equals");
        assertFalse(g1.equals(g3), "The grids contain different bubbles");
        var coll = g3.getBubbles();
        assertFalse(coll.isEmpty(), "The returned collection has bubbles");
        assertEquals(coll.size(), 4, "The returned collection has the bubbles of the grid");
        assertEquals(coll.size(), g3.getBubbles().size(), "The collections contain the same bubbles");
        final BubblesGrid g4 = new BubblesGridImpl(List.of(b1, b2, b3, b4), gridInfo);
        assertTrue(g4.getBubbles().containsAll(g3.getBubbles()),
                "Two grids with the same collection of bubbles are equals");
        coll = new ArrayList<Bubble>(coll);
        coll.add(b6);
        assertFalse(coll.equals(g3.getBubbles()),
                "Changes in the collection don't impact the grid, unless via grid methods");
        assertFalse(coll.equals(g4.getBubbles()),
                "Changes in the collection don't impact the grid, unless via grid methods");
        assertFalse(g1.equals(new BubblesGridImpl(new RegularHexGridInfo(10, 20, 1))),
                "Two grids with different gridInfo are not equals");
    }

    @Test
    public void testBubblesGridtoString() {
        final BubblesGrid g1 = new BubblesGridImpl(gridInfo);
        assertEquals(g1.toString(), "BubblesGridImpl [grid={}]", "The representation of an empty grid");
        g1.addBubble(b1);
        assertEquals(g1.toString(), "BubblesGridImpl [grid={TripletImpl [x=0, y=0, z=0]=" + b1.toString() + "}]",
                "The representation of a bubble");
        g1.removeBubble(b1.getCoords());
        g1.addBubble(b2);
        assertNotEquals(g1.toString(), "BubblesGridImpl []", "A grid with a bubble is not empty");

    }

    @Test
    public void testBubblesGridAdd() {
        final BubblesGrid g1 = new BubblesGridImpl(gridInfo);
        assertEquals(g1.getBubbles().size(), 0, "An empty grid has no bubbles");
        g1.addBubble(b1);
        assertEquals(g1.getBubbles().size(), 1, "The grid has only a bubble");
        g1.addBubble(b2);
        g1.addBubble(b3);
        assertEquals(g1.getBubbles().size(), 3, "The bubbles contained by the grid");
        g1.addBubble(b1);
        g1.addBubble(b4);
        assertEquals(g1.getBubbles().size(), 4, "A bubble already inside the grid isn't added");
        final var b4Clone = new BubbleImpl(b4.getCoords(), COLOR.PURPLE);
        g1.addBubble(b4Clone);
        assertEquals(g1.getBubbles().size(), 4, "A bubble already inside the grid isn't added");

        final var list = new ArrayList<Bubble>();
        list.add(b1);
        list.add(b2);
        final BubblesGrid g2 = new BubblesGridImpl(list, gridInfo);
        assertEquals(g2.getBubbles().size(), 2, "The original grid");
        list.add(b3);
        assertEquals(g2.getBubbles().size(), 2,
                "Changes in the collection don't impact the grid, unless via grid methods");
    }

    @Test
    public void testBubblesGridRemove() {
        final BubblesGrid g1 = new BubblesGridImpl(List.of(b1, b2, b3, b4), gridInfo);
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
        final BubblesGrid g2 = new BubblesGridImpl(list, gridInfo);
        assertEquals(g2.getBubbles().size(), 2, "The original grid");
        list.remove(b3);
        assertEquals(g2.getBubbles().size(), 2,
                "Changes in the collection don't impact the grid, unless via grid methods");
    }

    @Test
    public void testBubblesGridLastRowY() {
        final BubblesGrid g1 = new BubblesGridImpl(List.of(b1, b3, b5), gridInfo);
        final var b5Lowest = g1.getLastRowY();
        assertEquals(g1.getLastRowY(), b5Lowest, "The lowest bubble");
        g1.removeBubble(b3.getCoords());
        assertEquals(g1.getLastRowY(), b5Lowest, "Still the lowest bubble");
        g1.removeBubble(b5.getCoords());
        final var b1Lowest = g1.getLastRowY();
        assertEquals(g1.getLastRowY(), b1Lowest, "New lowest bubble");
        assertNotEquals(b1Lowest, b5Lowest, 0.0001, "The lowest bubble has changed");
        g1.removeBubble(b1.getCoords());
        assertEquals(g1.getLastRowY(), 0, "No bubbles are present, so the lowest is 0");
    }

    @Test
    public void testBubblesSameColorNeighbors() {
        final Bubble b7 = new BubbleImpl(new PositionImpl(0.49, 0.45), COLOR.RED);
        final Bubble b8 = new BubbleImpl(new PositionImpl(2, 0.45), COLOR.RED);
        final Bubble b9 = new BubbleImpl(new PositionImpl(0.82, 0.45), COLOR.RED);
        final BubblesGrid g1 = new BubblesGridImpl(gridInfo);
        assertTrue(g1.getSameColorNeighbors(b1).containsAll(List.of()),
                "No neighboring bubbles are present in an empty grid");
        assertEquals(g1.getBubbles().size(), 0, "An empty grid contains no bubbles");
        final BubblesGrid g2 = new BubblesGridImpl(List.of(b1, b2, b7, b8), gridInfo);
        assertEquals(g2.getSameColorNeighbors(b1).size(), 2, "The red neighbors");
        assertEquals(g2.getSameColorNeighbors(b2).size(), 1, "The orange neighbors");
        g2.addBubble(b9);
        assertEquals(g2.getSameColorNeighbors(b1).size(), 4, "Adding b9 connects b8 to the neighborhood of b1");
        assertEquals(g2.getSameColorNeighbors(b2).size(), 1, "The orange neighbors remain the same");
        g2.removeBubble(b7.getCoords());
        assertEquals(g2.getSameColorNeighbors(b1).size(), 1, "Removing b7 divides the red bubbles");
        assertEquals(g2.getSameColorNeighbors(b8).size(), 2, "Still neighbors after the removal of b7");
    }

    @Test
    public void testIsBubbleAttachable() {
        final BubblesGrid g1 = new BubblesGridImpl(gridInfo);
        assertTrue(g1.isBubbleAttachable(b1), "This bubble connects to the top of the grid");
        assertFalse(g1.isBubbleAttachable(b4), "This bubble can't be connected");
        g1.addBubble(b1);
        assertTrue(g1.isBubbleAttachable(b2), "This bubble connects to an existing bubble");
        assertFalse(g1.isBubbleAttachable(b4), "This bubble still can't be connected");
        g1.addBubble(b2);
        assertTrue(g1.isBubbleAttachable(b4), "This bubble connects to an existing bubble");
        g1.addBubble(b4);
        final Bubble bdx = new BubbleImpl(new PositionImpl(gridInfo.getPointsWidth() + 0.1, 0), COLOR.GREEN);
        assertFalse(g1.isBubbleAttachable(bdx), "This bubble is out of bounds");
        final Bubble bdown = new BubbleImpl(new PositionImpl(0, gridInfo.getPointsHeight() + 0.1), COLOR.GREEN);
        assertFalse(g1.isBubbleAttachable(bdown), "This bubble is out of bounds");

    }

    @Test
    public void testCheckForUnconnectedBubbles() {
        final BubblesGrid g1 = new BubblesGridImpl(gridInfo);
        assertTrue(g1.checkForUnconnectedBubbles().isEmpty(), "An empty grid has no unconnected bubbles");
        final BubblesGrid g2 = new BubblesGridImpl(List.of(b1, b2, b3, b4), gridInfo);
        assertTrue(g2.checkForUnconnectedBubbles().isEmpty(), "All bubbles are connected");
        final BubblesGrid g3 = new BubblesGridImpl(List.of(b1, b3, b6), gridInfo);
        assertFalse(g3.checkForUnconnectedBubbles().isEmpty(), "Some bubbles are unconnected");
        assertEquals(g3.checkForUnconnectedBubbles().size(), 1, "This bubble is unconnected");
        final BubblesGrid g4 = new BubblesGridImpl(List.of(b1, b4, b6), gridInfo);
        assertFalse(g4.checkForUnconnectedBubbles().isEmpty(), "Some bubbles are unconnected");
        assertEquals(g4.checkForUnconnectedBubbles().size(), 2, "These bubbles are unconnected");
        g4.removeBubble(b1.getCoords());
        assertEquals(g4.checkForUnconnectedBubbles().size(), 2, "These bubbles are unconnected");
        g4.removeBubble(b4.getCoords());
        g4.removeBubble(b6.getCoords());
        assertTrue(g1.checkForUnconnectedBubbles().isEmpty(), "All bubbles are unconnectes");

    }

    @Test
    public void testMoveBubblesDown() {
        final BubblesGridImpl g1 = new BubblesGridImpl(gridInfo);
        assertTrue(g1.getBubbles().isEmpty(), "An empty grid");
        g1.moveBubblesDown(1);
        assertTrue(g1.getBubbles().isEmpty(), "No changes by moving down no bubbles");
        final BubblesGrid g2 = new BubblesGridImpl(List.of(b1, b2, b3, b4), gridInfo);
        g2.moveBubblesDown(0);
        assertEquals(g2, new BubblesGridImpl(List.of(b1, b2, b3, b4), gridInfo),
                "Moving down by 0 rows doesn't change the grid");
        g2.moveBubblesDown(-4);
        assertEquals(g2, new BubblesGridImpl(List.of(b1, b2, b3, b4), gridInfo),
                "Moving down by a negative number doesn't change the grid");
        g2.moveBubblesDown(2);
        assertEquals(g2.getBubbles().size(), 4, "The grid has the same number of bubbles");

    }

    @Test
    public void testRemoveBubblesCascading() {
        final BubblesGridImpl g1 = new BubblesGridImpl(gridInfo);
        final var origGrid = g1.getBubbles();
        g1.removeBubblesCascading(b1.getCoords());
        final var finalGrid = g1.getBubbles();
        assertTrue(g1.checkForUnconnectedBubbles().isEmpty(), "An empty Grid has no unconnected bubbles");
        assertTrue(finalGrid.containsAll(origGrid), "An empty Grid has no unconnected bubbles");
        final BubblesGridImpl g2 = new BubblesGridImpl(List.of(b1, b2, b3, b4), gridInfo);
        g2.removeBubblesCascading(b2.getCoords());
        assertTrue(g2.checkForUnconnectedBubbles().isEmpty(), "All bubbles are connected");
        assertEquals(g2.getBubbles().size(), 3, "This grid has all connected bubbles");
        assertTrue(g2.checkForUnconnectedBubbles().isEmpty(),
                "All bubbles are connected, even after the removal of some");

        final BubblesGridImpl g3 = new BubblesGridImpl(List.of(b1, b2, b5), gridInfo);
        assertFalse(g3.checkForUnconnectedBubbles().isEmpty(), "Some bubbles are unconnected");
        g3.removeBubblesCascading(b5.getCoords());
        assertEquals(g3.getBubbles().size(), 2, "This grid has some unconnected bubbles");
        assertTrue(g3.checkForUnconnectedBubbles().isEmpty(), "All unconnected bubbles were removed");
        final BubblesGridImpl g4 = new BubblesGridImpl(List.of(b1, b2, b3, b4), gridInfo);
        assertTrue(g4.checkForUnconnectedBubbles().isEmpty(), "All bubbles are connected");
        g4.moveBubblesDown(1);
        assertFalse(g4.checkForUnconnectedBubbles().isEmpty(),
                "All bubbles are unconnected, after moving down the whole grid");
        g4.removeBubblesCascading(b1.getCoords());
        assertTrue(g4.getBubbles().isEmpty(), "All the bubbles were removed");

    }

    @Test
    public void testRemoveUnconnectedBubbles() {
        final BubblesGridImpl g1 = new BubblesGridImpl(gridInfo);
        var origGrid = g1.getBubbles();
        g1.removeUnconnectedBubbles();
        var finalGrid = g1.getBubbles();
        assertTrue(finalGrid.containsAll(origGrid), "An empty grid has no unconnected bubbles");
        final BubblesGridImpl g2 = new BubblesGridImpl(List.of(b1, b2, b3, b4), gridInfo);
        origGrid = g2.getBubbles();
        g2.removeUnconnectedBubbles();
        finalGrid = g2.getBubbles();
        assertTrue(finalGrid.containsAll(origGrid), "This grid has all connected bubbles");
        final BubblesGridImpl g3 = new BubblesGridImpl(List.of(b1, b2, b5), gridInfo);
        assertEquals(g3.getBubbles().size(), 3, "This grid has some unconnected bubbles");
        assertEquals(g3.checkForUnconnectedBubbles().size(), 1, "This grid has unconnected bubbles");
        g3.removeUnconnectedBubbles();
        assertEquals(g3.getBubbles().size(), 2, "This grid had some unconnected bubbles");
        assertTrue(g3.checkForUnconnectedBubbles().isEmpty(), "This grid is now all connected");
        final BubblesGridImpl g4 = new BubblesGridImpl(List.of(b1, b2, b3, b4), gridInfo);
        assertEquals(g4.getBubbles().size(), 4, "This grid has all connected bubbles");
        assertTrue(g4.checkForUnconnectedBubbles().isEmpty(), "This grid is all connected bubbles");
        g4.moveBubblesDown(1);
        assertEquals(g4.checkForUnconnectedBubbles().size(), 4, "This grid is now all unconnected");
        g4.removeUnconnectedBubbles();
        assertTrue(g4.getBubbles().isEmpty(), "This grid had some unconnected bubbles");
        assertTrue(g4.checkForUnconnectedBubbles().isEmpty(), "This grid is now empty");

    }

    @Test
    public void testEndReached() {
        final BubblesGridImpl g1 = new BubblesGridImpl(List.of(), gridInfo);
        assertFalse(g1.endReached(), "The grid is empty");
        final BubblesGridImpl g2 = new BubblesGridImpl(List.of(b1), gridInfo);
        assertFalse(g2.endReached(), "The grid hasn't got a bubble at the bottom");
        final BubblesGridImpl g3 = new BubblesGridImpl(
                List.of(new BubbleImpl(new PositionImpl(0, gridInfo.getPointsHeight()), COLOR.YELLOW)), gridInfo);
        assertTrue(g3.endReached(), "The grid has a bubble at the bottom");
        g3.removeUnconnectedBubbles();
        assertFalse(g3.endReached(), "The grid hasn't got a bubble at the bottom");
    }
}
