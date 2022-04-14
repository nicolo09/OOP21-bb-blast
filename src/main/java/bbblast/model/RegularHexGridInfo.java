package bbblast.model;

/**
 * 
 * A GridInfo made to generate a hex grid of regular hexagons
 *
 */
public class RegularHexGridInfo implements GridInfo {

    private final int bubbleWidth;
    private final int bubbleHeight;
    private final double ratio;
    
    /**
     * 
     * @param bubbleWidth grid's width in bubbles
     * @param bubbleHeight grid's height in bubbles
     * @param pointBubbleRatio width of a bubble in points (2*apothem in a hex grid)
     */
    public RegularHexGridInfo(final int bubbleWidth, final int bubbleHeight, final double pointBubbleRatio) {
        this.bubbleWidth = bubbleWidth;
        this.bubbleHeight = bubbleHeight;
        this.ratio = pointBubbleRatio;
    }

    @Override
    public int getBubbleWidth() {
        return this.bubbleWidth;
    }

    @Override
    public int getBubbleHeight() {
        return this.bubbleHeight;
    }

    @Override
    public double getPointsWidth() {
        return this.bubbleWidth * this.ratio;
    }
    
    @Override
    public double getPointsHeight() {
        return this.bubbleHeight * this.getSingleBubbleHeight();
    }

    //(4*apotema) / radice(3)
    private double getSingleBubbleHeight() {
        return 2*this.ratio / Math.sqrt(3);
    }
}
