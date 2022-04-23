package bbblast.model.level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import bbblast.model.BubbleGenerator;
import bbblast.model.BubbleGeneratorImpl;
import bbblast.model.COLOR;
import bbblast.model.GridInfo;
import bbblast.model.RegularHexGridInfo;
import bbblast.utils.persister.FilePersister;
import bbblast.utils.persister.Persister;

public class LevelTest {
	private static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    private static final Path PATH = Path
            .of(System.getProperty("user.home") + SEPARATOR + ".bbblast-test" + SEPARATOR + "levelTest.json");
	@Test
	public void testLevelCreation() {
		final GridInfo infos = new RegularHexGridInfo(10, 10, 1);
		final BubbleGenerator generator = new BubbleGeneratorImpl(List.of(COLOR.values()));
		final Level lvl = new LevelImpl(infos, generator);
		
		lvl.fillGameBubblesGrid(1);
		assertEquals(infos.getBubbleWidth(), lvl.getGameBubblesGrid().getBubbles().size(), "The first row should be filled");
		lvl.fillGameBubblesGrid(1);
		//Talk about it
		//assertNotEquals(2 * infos.getBubbleWidth(), lvl.getGameBubblesGrid().getBubbles().size(), "Not all the rows contain the same number of Bubbles");
		System.out.println(lvl.getGameBubblesGrid());
	}
	
	@Test
	public void testLevelSavingAndLoading() {
		final GridInfo saveInfos = new RegularHexGridInfo(20, 50, 50);
		final BubbleGenerator saveGenerator = new BubbleGeneratorImpl(List.of(COLOR.BLUE, COLOR.RED, COLOR.GREEN));
		final BubbleGenerator differentGenerator = new BubbleGeneratorImpl(List.of(COLOR.values()));
		final Level saveLevel = new LevelImpl(saveInfos, saveGenerator);
		final Level sameLevel = new LevelImpl(saveInfos, saveGenerator);
		final Level differentLevel = new LevelImpl(saveInfos, differentGenerator);
		final Persister<Level> persister = new FilePersister<>(PATH, Level.class);
		
		try {
			persister.save(saveLevel);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Couldn't save Level");
		}
		
		final var loadLevel = persister.load().get();
		assertEquals(loadLevel, saveLevel, "Original and loaded levels should be equals");
		assertNotEquals(loadLevel, differentLevel, "Two Levels are different if they can generate different Bubbles");
	}

}
