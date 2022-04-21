package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ColorTest {
    @Test
    public void allExceptGrey() {
        assertEquals(COLOR.values().length - 1, COLOR.allExceptGrey().size(), "All except gray method returned a collections with wrong length");
        assertFalse(COLOR.allExceptGrey().contains(COLOR.GREY),"All except gray method returned a collections that contains grey");
    }
}
