package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
/**
 * Test class for {@link COLOR} enum.
 */
public class ColorTest {

    /**
     * Tests the method which returns all colors but grey.
     */
    @Test
    public void allExceptGrey() {
        assertEquals(COLOR.values().length - 1, COLOR.allExceptGrey().size(), "All except gray method returned a collections with wrong length");
        assertFalse(COLOR.allExceptGrey().contains(COLOR.GREY), "All except gray method returned a collections that contains grey");
    }
}
