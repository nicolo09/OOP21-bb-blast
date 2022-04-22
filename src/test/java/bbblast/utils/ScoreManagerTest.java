package bbblast.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import bbblast.utils.persister.FilePersister;

public class ScoreManagerTest {

    private static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    private static final Path PATH = Path
            .of(System.getProperty("user.home") + SEPARATOR + ".bbblast-test" + SEPARATOR + "ScoreManagerTest.json");
    private final List<Score> l = new ArrayList<>();

    private final ScoreManager x = new ScoreManagerImpl(
            new FilePersister<List<Score>>(PATH, (Class<List<Score>>) l.getClass()));

    @Test
    public void TestScoreManager() {
        // test 1
        assertNotNull(x, "It was not created");

        // test 2
        x.resetScores();
        
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

        // test 3
        x.resetScores();
        assertEquals(x.load().size(), 0, "It hasn't been resetted");

    }

}
