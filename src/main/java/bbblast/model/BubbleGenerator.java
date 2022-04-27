package bbblast.model;

import bbblast.utils.Position;
/**
 * 
 * The interface which model a Bubble Generator.
 * 
 */
public interface BubbleGenerator {
    /**
     * @param p is the bubble position.
     * @return random color generated bubble
     */
    Bubble generate(Position p);

}
