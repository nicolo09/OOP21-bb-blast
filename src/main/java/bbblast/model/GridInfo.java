package bbblast.model;

/**
 * Give informations about a bubble grid
 */
public interface GridInfo {
    /**
     * 
     * @return the grid's width in bubbles
     */
    int getBubbleWidth();
    
    /**
     * 
     * @return the grid's height in bubbles
     */
    int getBubbleHeight();
    
    /**
     * 
     * @return the grid's width in points
     */
    double getPointsWidth();
    
    /**
     * 
     * @return the grid's width in points
     */
    double getPointsHeight();

}
