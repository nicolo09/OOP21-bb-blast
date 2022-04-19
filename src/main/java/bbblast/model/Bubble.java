package bbblast.model;

import bbblast.utils.Position;

/**
 * 
 * The Interface which models a Bubble.
 *
 */
public interface Bubble {

    /**
     * @return the position of the Bubble
     */
    Position getCoords();

    /**
     * @return the color of the Bubble
     */
    COLOR getColor();

    /**
     * @param p the position to add so that the Bubble is in position (x + px, y +
     *          py).
     */
    void moveBy(Position p);

}
