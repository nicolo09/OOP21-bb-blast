package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import bbblast.utils.PositionImpl;

public class MovingBubbleTest {

	@Test
	public void testMovingBubbleEquals() {
		final Bubble b = new BubbleImpl(new PositionImpl(0, 0), COLOR.BLUE);
		final MovingBubble m1 = new MovingBubbleImpl(new PositionImpl(0, 0), COLOR.BLUE);
		final MovingBubble m2 = new MovingBubbleImpl(new PositionImpl(0, 0), COLOR.RED);
		final MovingBubble m3 = new MovingBubbleImpl(new PositionImpl(0, 0), COLOR.RED);
		m3.setSpeed(new PositionImpl(1, 1));
		
		assertFalse(b.equals(m1), "Two different types of bubbles are not the same");
		assertFalse(m1.equals(m2), "Two bubbles with same position same speed but different color are not equals");
		assertEquals(m2, m3, "Two bubble with same position and color but different speed are equals");
	}
}
