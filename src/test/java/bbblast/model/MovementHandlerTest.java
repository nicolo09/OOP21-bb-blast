package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertFalse;

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
	
	@Test
	public void testMovementHandlerCorrectness() {
		final BubblesGrid grid = new BubblesGridImpl(List.of(b1, b2, b3, b4), infos);
		final MovementHandler handler = new MovementHandlerImpl(grid, infos);
		final MovingBubble m = new MovingBubbleImpl(base);
		m.setSpeed(new PositionImpl(1, -1));
		
		assertFalse(handler.handle(), "No MovingBubble was set");
	}
}
