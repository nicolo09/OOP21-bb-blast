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

}
