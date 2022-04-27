package bbblast.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * This class tests the methods of {@link Score}.
 */
public class ScoreTest {

    private final ScoreImpl s1 = new ScoreImpl("lorenzo", 1000);
    private final ScoreImpl s2 = new ScoreImpl("emma", 8);

    /**
     * This test check if the constructor works.
     */
    @Test
    public void testScore() {
        assertTrue(s1.getName().equals("lorenzo"), "The two names different");
        assertSame(s1.getScoreValue(), (1000), "The two score are different");
        assertNotEquals(s1.getDate(), null, "The date exist");
    }
    /**
     * This test check if the two score have been created differently.
     */
    @Test
    public void testDifferet() {
        assertFalse(s2.equals(s1), "They are different");
    }
}
