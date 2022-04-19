package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import bbblast.utils.PositionImpl;

public class MovementHandlerTest {

	//generic test subjects
	private final GridInfo infos = new RegularHexGridInfo(10, 10, 1);
	private final Bubble b1 = new BubbleImpl(new PositionImpl(0, 0), COLOR.RED);
	private final Bubble b2 = new BubbleImpl(new PositionImpl(1, 0), COLOR.BLUE);
	private final Bubble b3 = new BubbleImpl(new PositionImpl(2, 0), COLOR.GREEN);
	private final Bubble b4 = new BubbleImpl(new PositionImpl(3, 0), COLOR.YELLOW);
	private final Bubble base = new BubbleImpl(new PositionImpl(infos.getBubbleWidth()/2 + 0.5, infos.getBubbleHeight()), COLOR.PURPLE);
	private final BubblesGrid grid = new BubblesGridImpl(List.of(b1, b2, b3, b4), infos);
	private final MovementHandler handler = new MovementHandlerImpl(grid, infos);
	
	@Test
	public void testMovementHandlerCorrectness() {
		final MovingBubble m1 = new MovingBubbleImpl(base);
		final MovingBubble m2 = new MovingBubbleImpl(base);
		m1.setSpeed(new PositionImpl(0, -1));
		m2.setSpeed(new PositionImpl(0, 1));
		
		assertFalse(handler.handle(), "No MovingBubble was set");
		handler.setShot(m1);
		assertTrue(handler.handle(), "MovingBubble is set and can move");
		handler.setShot(m2);
		assertFalse(handler.handle(), "The current shot has a positive Y velocity, which the handler can't handle");
	}
	
	@Test
	public void testMovementHandlerManagement() {
		final MovingBubble m1 = new MovingBubbleImpl(base);
		final MovingBubble m2 = new MovingBubbleImpl(new PositionImpl(infos.getBubbleWidth() - 1, infos.getBubbleHeight()), COLOR.ORANGE);
		final MovingBubble m3 = new MovingBubbleImpl(new PositionImpl(0.6, infos.getBubbleHeight()), COLOR.ORANGE);
		final MovingBubble m4 = new MovingBubbleImpl(base);
		m1.setSpeed(new PositionImpl(0, -1));
		m4.setSpeed(new PositionImpl(0, -1));
		handler.setShot(m1);
		
		for (int i = 0; i < infos.getBubbleHeight(); i++) {
			handler.handle();
		}
		assertFalse(handler.handle(), "The handler shouldn't be able to move the shot because it reached the top");
		assertEquals(5, grid.getBubbles().size(), "Now the grid should also contain the shot");
		
		m2.setSpeed(new PositionImpl(0.7, 0));
		handler.setShot(m2);
		assertTrue(handler.handle(), "The shot should be able to move");
		assertEquals(infos.getBubbleWidth() - 0.7, m2.getCoords().getX(), 0.001, "The shot moved 0.5 points forward and came back 0.2");
		
		m3.setSpeed(new PositionImpl(- 0.9, 0));
		handler.setShot(m3);
		assertTrue(handler.handle(), "The shot should be able to move");
		assertEquals(1.3, m3.getCoords().getX(), 0.001, "The shot moved 0.1 points forward and came back 0.8");
		
		handler.setShot(m4);
		while(handler.handle()); //cycles until handler can't move the shot anymore 
		assertEquals(6, grid.getBubbles().size(), "Now the grid should also contain the shot");
		
	}
}
