package bbblast.model.level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import bbblast.model.BubbleGenerator;
import bbblast.model.BubbleGeneratorImpl;
import bbblast.model.COLOR;
import bbblast.model.GridInfo;
import bbblast.model.RegularHexGridInfo;

public class LevelTest {
	
	@Test
	public void testLevelCreation() {
		final GridInfo infos = new RegularHexGridInfo(10, 10, 1);
		final BubbleGenerator generator = new BubbleGeneratorImpl(List.of(COLOR.values()));
		final Level lvl = new LevelImpl(infos, generator);
		
		lvl.fillGameBubblesGrid(1);
		assertEquals(infos.getBubbleWidth(), lvl.getGameBubblesGrid().getBubbles().size(), "The first row should be filled");
		lvl.fillGameBubblesGrid(1);
		assertNotEquals(2 * infos.getBubbleWidth(), lvl.getGameBubblesGrid().getBubbles().size(), "Not all the rows contain the same number of Bubbles");
		System.out.println(lvl.getGameBubblesGrid());
	}

}
