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

/**
 * 
 * Test class for {@link LevelImpl}.
 *
 */
public class LevelTest {
    private static final int FPS = 60;
    private static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    private static final Path PATH = Path
            .of(System.getProperty("user.home") + SEPARATOR + ".bbblast-test" + SEPARATOR + "levelTest.json");

    /**
     * Testing basic Level's methods.
     */
    @Test
    public void testLevelCreation() {
        final GridInfo infos = new RegularHexGridInfo(10, 10, 1);
        final BubbleGenerator generator = new BubbleGeneratorImpl(List.of(COLOR.values()));
        final Level lvl = new LevelImpl(infos, generator, FPS);

        lvl.fillGameBubblesGrid(1);
        assertEquals(infos.getBubbleWidth(), lvl.getGameBubblesGrid().getBubbles().size(),
                "The first row should be filled");
        lvl.fillGameBubblesGrid(1);
        assertEquals(2 * infos.getBubbleWidth(), lvl.getGameBubblesGrid().getBubbles().size(),
                "Now the grid contains two rows");
        System.out.println(lvl.getGameBubblesGrid());
    }

    /**
     * Testing if saving and loading of a Level works properly.
     */
    @Test
    public void testLevelSavingAndLoading() {
        final GridInfo saveInfos = new RegularHexGridInfo(20, 50, 50);
        final BubbleGenerator saveGenerator = new BubbleGeneratorImpl(List.of(COLOR.BLUE, COLOR.RED, COLOR.GREEN));
        final Level saveLevel = new LevelImpl(saveInfos, saveGenerator, FPS);
        final Persister<Level> persister = new FilePersister<>(PATH, Level.class);

        try {
            persister.save(saveLevel);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Couldn't save Level");
        }

        final var loadLevel = persister.load().get();
        assertEquals(loadLevel, saveLevel, "Original and loaded levels should be equals");
    }

    /**
     * Testing LevelImpl's equals method.
     */
    @Test
    public void testLevelEquals() {
        final GridInfo originInfos = new RegularHexGridInfo(10, 10, 2);
        final GridInfo differentInfos = new RegularHexGridInfo(10, 10, 1);
        final BubbleGenerator originGenerator = new BubbleGeneratorImpl(List.of(COLOR.BLUE, COLOR.RED, COLOR.GREEN));
        final BubbleGenerator differentGenerator = new BubbleGeneratorImpl(List.of(COLOR.values()));
        final Level originLvl = new LevelImpl(originInfos, originGenerator, FPS);
        final Level differentLvl1 = new LevelImpl(differentInfos, originGenerator, FPS);
        final Level differentLvl2 = new LevelImpl(originInfos, differentGenerator, FPS);
        final Level differentLvl3 = new LevelImpl(originInfos, originGenerator, FPS);

        assertNotEquals(originLvl, differentLvl1, "Two levels with different grid infos are different");
        assertNotEquals(originLvl, differentLvl2, "Two levels with different generators are different");
        differentLvl3.fillGameBubblesGrid(1); // Filling the first row
        assertNotEquals(originLvl, differentLvl3, "Two levels initially equals are different when filling a row");
        // NOTE: when generating two levels with same parameters they could still be
        // different, because the Cannon is generated randomly
    }

}
