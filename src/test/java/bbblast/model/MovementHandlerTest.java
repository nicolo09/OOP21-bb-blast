package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import bbblast.utils.Position;
import bbblast.utils.PositionImpl;

/**
 * 
 * Test class for {@link MovementHandlerImpl}.
 *
 */
public class MovementHandlerTest {

    // generic test subjects
    private final GridInfo infos = new RegularHexGridInfo(10, 10, 1);
    private final Consumer<Bubble> consumer = (a -> {
    });
    private final Bubble b1 = new BubbleImpl(new PositionImpl(0, 0), COLOR.RED);
    private final Bubble b2 = new BubbleImpl(new PositionImpl(1, 0), COLOR.BLUE);
    private final Bubble b3 = new BubbleImpl(new PositionImpl(2, 0), COLOR.GREEN);
    private final Bubble b4 = new BubbleImpl(new PositionImpl(3, 0), COLOR.YELLOW);
    private final Bubble base = new BubbleImpl(
            new PositionImpl(infos.getPointsWidth() / 2 + 0.5, infos.getPointsHeight()), COLOR.PURPLE);
    private final BubblesGrid grid = new BubblesGridImpl(List.of(b1, b2, b3, b4), infos);
    private final MovementHandler handler = new MovementHandlerImpl(grid, infos, consumer);

    /**
     * Testing basic MovementHandler's methods.
     */
    @Test
    public void testMovementHandlerCorrectness() {
        final MovingBubble m1 = new MovingBubbleImpl(base);
        final MovingBubble m2 = new MovingBubbleImpl(base);
        m1.setSpeed(new PositionImpl(0, -1));
        m2.setSpeed(new PositionImpl(0, 1));

        assertFalse(handler.handle(), "No MovingBubble was set");
        assertEquals(Optional.empty(), handler.getShot(), "When there is no shot set it returns an empty Optional");
        handler.setShot(m1);
        assertEquals(Optional.of(m1), handler.getShot(), "Should return the optional of the currently shot set");
        assertTrue(handler.handle(), "MovingBubble is set and can move");
        handler.setShot(m2);
        assertFalse(handler.handle(), "The current shot has a positive Y velocity, which the handler can't handle");
    }

    /**
     * Testing movement management.
     */
    @Test
    public void testMovementHandlerManagement() {
        final double margin = 0.001;
        final Position speed1 = new PositionImpl(0.7, 0);
        final Position speed2 = new PositionImpl(-0.9, 0);
        final Position speed3 = new PositionImpl(0.6, -0.8);
        final MovingBubble m1 = new MovingBubbleImpl(base);
        final MovingBubble m2 = new MovingBubbleImpl(
                new PositionImpl(infos.getPointsWidth() - 1, infos.getPointsHeight()), COLOR.ORANGE);
        final MovingBubble m3 = new MovingBubbleImpl(new PositionImpl(0.6, infos.getPointsHeight()), COLOR.ORANGE);
        final MovingBubble m4 = new MovingBubbleImpl(base);
        final MovingBubble m5 = new MovingBubbleImpl(
                new PositionImpl(infos.getPointsWidth() - 1, infos.getPointsHeight()), COLOR.ORANGE);
        m1.setSpeed(new PositionImpl(0, -1));
        m4.setSpeed(new PositionImpl(0, -1));
        handler.setShot(m1);

        var previousDimension = grid.getBubbles().size();
        for (int i = 0; i < Math.round(infos.getPointsHeight()); i++) {
            handler.handle();
        }
        assertFalse(handler.handle(), "The handler shouldn't be able to move the shot because it reached the top");
        assertEquals(previousDimension + 1, grid.getBubbles().size(), "Now the grid should also contain the shot");

        m2.setSpeed(speed1);
        handler.setShot(m2);
        var correctX = infos.getPointsWidth() - speed1.getX();
        assertTrue(handler.handle(), "The shot should be able to move");
        assertEquals(correctX, m2.getCoords().getX(), margin, "The shot moved 0.5 points forward and came back 0.2");

        m3.setSpeed(speed2);
        handler.setShot(m3);
        correctX = infos.getBubbleRadius() - speed2.getX() + (infos.getBubbleRadius() - m3.getCoords().getX());
        assertTrue(handler.handle(), "The shot should be able to move");
        assertEquals(correctX, m3.getCoords().getX(), margin, "The shot moved 0.1 points forward and came back 0.8");

        handler.setShot(m4);
        previousDimension = grid.getBubbles().size();
        while (handler.handle()) {
            System.out.println(); //otherwise it generates a warning
        } 
        // cycles until handler can't move the shot anymore
        assertEquals(previousDimension + 1, grid.getBubbles().size(), "Now the grid should also contain the shot");

        m5.setSpeed(speed3);
        handler.setShot(m5);
        correctX = infos.getPointsWidth() - speed3.getX();
        handler.handle();
        assertEquals(new PositionImpl(correctX, infos.getPointsHeight() + speed3.getY()), m5.getCoords(),
                "The shot bounced on the right wall while going up");

    }

    /**
     * Testing attachment to the grid.
     */
    @Test
    public void testAttachmentToGenericGrid() {
        final BubbleGenerator generator = new BubbleGeneratorImpl(List.of(COLOR.values()));
        final GridInfo genericInfo = new RegularHexGridInfo(20, 50, 50);
        final MovingBubble mb = new MovingBubbleImpl(generator.generate(new PositionImpl(
                genericInfo.getPointsWidth() / 2 + genericInfo.getBubbleRadius(), genericInfo.getPointsHeight())));
        final Position speed = new PositionImpl(5, -10);
        final MovingBubble test1 = new MovingBubbleImpl(mb);
        final MovingBubble test2 = new MovingBubbleImpl(mb);
        test1.setSpeed(speed);
        test2.setSpeed(speed);

        final BubblesGrid genericGrid = new BubblesGridImpl(genericInfo);
        final MovementHandler genericHandler = new MovementHandlerImpl(genericGrid, genericInfo, consumer);
        genericHandler.setShot(test1);

        while (genericHandler.handle()) {
            System.out.println(); //otherwise it generates a warning
        }
        assertEquals(1, genericGrid.getBubbles().size(), "Empty grid now contains a bubble somewhere on the top");

        for (int i = 0; i < genericInfo.getPointsWidth(); i++) {
            genericGrid.addBubble(generator.generate(new PositionImpl(i, genericInfo.getBubbleRadius())));
        }
        genericHandler.setShot(test2);
        final var previousDimension = genericGrid.getBubbles().size();
        while (genericHandler.handle()) {
            System.out.println(); //otherwise it generates a warning
        }
        assertEquals(previousDimension + 1, genericGrid.getBubbles().size(), "Now the grid should have a second coloumn");
    }
}
