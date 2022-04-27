package bbblast.model;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents the possible bubble colors. This is a model representation, and as
 * such may not be connected to the way view represents bubbles.
 */
public enum COLOR {

    /**
     * Blue color.
     */
    BLUE,
    /**
     * Green color.
     */
    GREEN,
    /**
     * Orange color.
     */
    ORANGE,
    /**
     * Purple color.
     */
    PURPLE,
    /**
     * Red color.
     */
    RED,
    /**
     * Yellow color.
     */
    YELLOW,
    /**
     * Green color.
     */
    GREY;

    static Collection<COLOR> allExceptGrey() {
        return Stream.of(COLOR.values()).filter(a -> !a.equals(GREY)).collect(Collectors.toSet());
    }

}
