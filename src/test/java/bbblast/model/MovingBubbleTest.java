package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bbblast.utils.Position;
import bbblast.utils.PositionImpl;

public class MovingBubbleTest {

	@Test
	public void testMovingBubbleEquals() {
		final Bubble b = new BubbleImpl(new PositionImpl(0, 0), COLOR.BLUE);
		final MovingBubble m1 = new MovingBubbleImpl(new PositionImpl(0, 0), COLOR.BLUE);
		final MovingBubble m2 = new MovingBubbleImpl(new PositionImpl(0, 0), COLOR.RED);
		final MovingBubble m3 = new MovingBubbleImpl(new PositionImpl(0, 0), COLOR.RED);
		final Bubble copy = m1.getStationaryCopy();
		m3.setSpeed(new PositionImpl(1, 1));

		assertFalse(copy.equals(m1), "The stationary copy isn't the same as the original");
		assertFalse(b.equals(m1), "Two different types of bubbles are not the same");
		assertFalse(m1.equals(m2), "Two bubbles with same position same speed but different color are not equals");
		assertEquals(m2, m3, "Two bubble with same position and color but different speed are equals");
	}

	@Test
	public void testMovingBubbleCorrectness() {
		Bubble copy;
		final Position p1 = new PositionImpl(0, 0);
		final Position p2 = new PositionImpl(1, 1);
		final MovingBubble m1 = new MovingBubbleImpl(p1, COLOR.GREEN);
		final MovingBubble m2 = new MovingBubbleImpl(p2, COLOR.GREEN);
		m1.setSpeed(p2);
		assertTrue(m1.getSpeedX() != 0 && m1.getSpeedY() != 0, "The speed has changed");
		m1.move();
		assertEquals(m1, m2, "The two bubbles are now in the same position");
		p2.setCoords(2, 2);
		assertFalse(m1.getSpeedX() == p2.getX() || m1.getSpeedY() == p2.getY(), "The speed hasn't changed");
		m1.swapSpeedX();
		assertTrue(m1.getSpeedX() < 0, "The bubble now moves in the opposite x direction");
		copy = m1.getStationaryCopy();
		assertTrue(copy.getCoords().equals(m1.getCoords()) && copy.getColor().equals(m1.getColor()),
				"The copy and the original have the same position and color");
		assertEquals(m1.toString(), "Moving" + copy.toString() + ", " + new PositionImpl(-1, 1).toString(), "Wrong string rappresentation");
	}
	
	@Test
	public void testMovingBubbleMovement() {
		final MovingBubble m = new MovingBubbleImpl(new PositionImpl(0, 0), COLOR.PURPLE);
		m.setSpeed(new PositionImpl(1, 1));
		for(int i = 0; i < 10; i++) {
			m.move();
		}
		assertEquals(m.getCoords(), new PositionImpl(10, 10),"The bubble moved of 10 points on both x and y axis");
		m.swapSpeedX();
		for(int i = 0; i < 10; i++) {
			m.move();
		}
		assertEquals(m.getCoords().getX(), 0, "The bubble changed its x direction and moved 10 times");
	}
}
