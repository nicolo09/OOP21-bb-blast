package bbblast.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import bbblast.utils.persister.FilePersister;
/**
 * This class tests methods of {@link ScoreManager}.
 */
public class ScoreManagerTest {

    private static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    private static final Path PATH = Path
            .of(System.getProperty("user.home") + SEPARATOR + ".bbblast-test" + SEPARATOR + "ScoreManagerTest.json");

    private final ScoreManager x = new ScoreManagerImpl(new FilePersister<ScoreTable>(PATH, ScoreTable.class));
    /**
     * This test control if the score list has been created.
     */
    @Test
    public void testScoreManager() {

        assertNotNull(x, "It was not created");
        x.resetScores();
    }
    /**
     * This test check if the list can be upgraded with different scores.
     */

    @Test
    public void testScoreUpgrade() {
        final ScoreImpl s1 = new ScoreImpl("nicolo", 2000);
        final ScoreImpl s2 = new ScoreImpl("emma", 1500);
        final ScoreImpl s3 = new ScoreImpl("casa", 4000);
        final List<ScoreImpl> y = new ArrayList<>();
        y.add(s1);
        y.add(s2);
        y.add(s3);
        x.save(s1);
        x.save(s2);
        x.save(s3);
        assertEquals(new ArrayList<>(x.load()), y, "They are not the same");
    }
    /**
     * This test chek if the resetScore method work.
     */
    @Test
    public void testScoreReset() {
        x.resetScores();
        assertEquals(x.load().size(), 0, "It hasn't been resetted");
    }
}
