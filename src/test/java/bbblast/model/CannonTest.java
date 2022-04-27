package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import bbblast.utils.PositionImpl;

/***
 * 
 * This class tests methods of {@link Cannon}.
 */
public class CannonTest {

    private final BubbleGenerator bbgen = new BubbleGeneratorImpl(
            List.of(COLOR.RED, COLOR.GREEN, COLOR.YELLOW, COLOR.BLUE));

    /***
     * 
     * This test checks constructor for {@link Cannon}.
     */
    @Test
    public void testCannonPersistence() {
        final Cannon c = new CannonImpl(new PositionImpl(0, 0), 60, 60, bbgen);
        assertFalse(c.toString().equals(""), "Cannon has a toString");
    }

    /***
     * 
     * This test checks getAngle and move for {@link Cannon}.
     */
    @Test
    public void testCannonMove() {
        final Cannon c = new CannonImpl(new PositionImpl(0, 0), 60, 60, bbgen);
        final int start = c.getAngle();
        final int change = 45;
        c.move(change);
        final int end = c.getAngle();
        assertEquals(start, CannonImpl.START_ANGLE, "Cannon starts at starting value degrees");
        assertNotEquals(start, end, "Moving the cannon changes the angle");
        assertEquals(change, c.getAngle(), "The cannon angle is set correctly");
        c.move(CannonImpl.START_ANGLE);
        c.move(-change);
        assertEquals(c.getAngle(), CannonImpl.START_ANGLE, "The angle is negative, so it wasn't changed");
        c.move(CannonImpl.START_ANGLE);
        c.move(CannonImpl.MIN_ANGLE - 1);
        assertEquals(c.getAngle(), CannonImpl.START_ANGLE, "The angle is outside of the range, so it wasn't changed");
        c.move(CannonImpl.START_ANGLE);
        c.move(CannonImpl.MAX_ANGLE + 1);
        assertEquals(c.getAngle(), CannonImpl.START_ANGLE, "The angle is outside of the range, so it wasn't changed");
    }

    /***
     * 
     * This test checks getCurrentlyLoadedBubble for {@link Cannon}.
     */
    @Test
    public void testGetCurrentlyLoadedBubble() {
        final Cannon c = new CannonImpl(new PositionImpl(0, 0), 60, 60, bbgen);
        final var b1 = c.getCurrentlyLoadedBubble();
        final var b2 = c.shoot();
        assertEquals(b1, b2, "The bubble currently loaded is the one shot");

    }

    /***
     * 
     * This test checks shoot for {@link Cannon}.
     */
    @Test
    public void testCannonShoot() {
        final Cannon c = new CannonImpl(new PositionImpl(0, 0), 60, 60, bbgen);
        assertNotEquals(c.shoot(), null, "A bubble isn't null");
        c.move(CannonImpl.MAX_ANGLE);
        assertNotEquals(c.shoot(), null, "A bubble isn't null");
        c.move(CannonImpl.MAX_ANGLE);
        assertNotEquals(c.shoot(), null, "A bubble isn't null");

    }

}
