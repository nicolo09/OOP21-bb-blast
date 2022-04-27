package bbblast.model;

import java.io.Serializable;

import bbblast.utils.Position;
import bbblast.utils.PositionImpl;

/**
 * 
 * The class which models a Bubble that can move on a 2D surface.
 *
 */
public class MovingBubbleImpl extends BubbleImpl implements MovingBubble, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6864076588481992337L;
    private final Position speed;

    /**
     * Generates a new moving Bubble which has a velocity of 0.
     * 
     * @param p the position of this Bubble
     * @param c the color of this Bubble
     */
    public MovingBubbleImpl(final Position p, final COLOR c) {
        super(p, c);
        this.speed = new PositionImpl(0, 0);
    }

    /**
     * Generates a new MovingBubble, from an existing Bubble, which has a velocity
     * of 0.
     * 
     * @param b the bubble that has to be converted to MovingBubble
     */
    public MovingBubbleImpl(final Bubble b) {
        this(b.getCoords(), b.getColor());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final Position speed) {
        this.speed.setCoords(speed.getX(), speed.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeedX() {
        return this.speed.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeedY() {
        return this.speed.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        super.moveBy(this.speed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void swapSpeedX() {
        this.speed.setCoords(-getSpeedX(), getSpeedY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bubble getStationaryCopy() {
        return new BubbleImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Moving" + super.toString() + ", " + this.speed.toString();
    }

}
