package bbblast.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * A GridInfo that models a grid of regular hexagons.
 *
 */
public class RegularHexGridInfo implements GridInfo, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7403615975326048978L;
    private final int bubbleWidth;
    private final int bubbleHeight;
    private final double ratio;

    /**
     * 
     * @param bubbleWidth      grid's width in bubbles
     * @param bubbleHeight     grid's height in bubbles
     * @param pointBubbleRatio width of a bubble in points (equivalent to 2*apothem
     *                         in a hex grid)
     */
    public RegularHexGridInfo(final int bubbleWidth, final int bubbleHeight, final double pointBubbleRatio) {
        this.bubbleWidth = bubbleWidth;
        this.bubbleHeight = bubbleHeight;
        this.ratio = pointBubbleRatio;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBubbleWidth() {
        return this.bubbleWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBubbleHeight() {
        return this.bubbleHeight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPointsWidth() {
        return this.bubbleWidth * this.ratio + (this.ratio / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPointsHeight() {
        return (double) 3 / 4 * (2 * (this.ratio / Math.sqrt(3)) * (this.bubbleHeight - 1))
                + 2 * (this.ratio / Math.sqrt(3));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(bubbleHeight, bubbleWidth, ratio);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegularHexGridInfo other = (RegularHexGridInfo) obj;
        return this.bubbleHeight == other.bubbleHeight && this.bubbleWidth == other.bubbleWidth
                && Double.compare(this.ratio, other.ratio) == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RegularHexGridInfo [bubbleWidth=" + this.bubbleWidth + ", bubbleHeight=" + this.bubbleHeight
                + ", ratio=" + this.ratio + "]";
    }

    /**
     * {@inheritDoc} In this implementation the bubble radius is equivalent to the
     * apothem of the hexagon.
     */
    @Override
    public double getBubbleRadius() {
        return ratio / 2;
    }
}
