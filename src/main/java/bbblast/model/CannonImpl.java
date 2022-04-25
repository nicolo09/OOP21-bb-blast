package bbblast.model;

import java.io.Serializable;
import java.util.Objects;

import bbblast.utils.Position;
import bbblast.utils.PositionImpl;
import bbblast.utils.VectorConverter;
import bbblast.utils.VectorConverterImpl;

/**
 * 
 * This class implements a Cannon.
 *
 */
public class CannonImpl implements Cannon, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5046837992787988716L;
	private int angle = START_ANGLE;
    private MovingBubble loadedBubble;
    private final Position startingPosition;
    private final BubbleGenerator bbGenerator;
    private final VectorConverter vectorConv;

    /**
     * This value is the minimum angle the cannon can reach.
     */
    public static final int MIN_ANGLE = 5;
    /**
     * This value is the maximum angle the cannon can reach.
     */
    public static final int MAX_ANGLE = 175;
    /**
     * This value is the cannon starts at.
     */
    public static final int START_ANGLE = 90;

    /**
     * @param p           the position at which bubbles will inially spawn.
     * @param fps         the frames per second.
     * @param bbGenerator the bubbleGenerator that decides the color of the bubbles.
     * @param speed       the speed at which the bubble is shot.
     * 
     */
    public CannonImpl(final Position p, final int fps, final int speed, final BubbleGenerator bbGenerator) {
        this.startingPosition = new PositionImpl(p.getX(), p.getY());
        this.bbGenerator = bbGenerator;
        this.loadedBubble = new MovingBubbleImpl(this.bbGenerator.generate(this.startingPosition));
        this.vectorConv = new VectorConverterImpl(fps);
        this.vectorConv.setModule(speed);
    }

    /***
     * {@inheritDoc} The angle has to be between MIN_ANGLE and MAX_ANGLE degrees.
     */
    @Override
    public void move(final int angle) {
        if (angle >= MIN_ANGLE && angle <= MAX_ANGLE) {
            this.angle = angle;
        }
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public Bubble getCurrentlyLoadedBubble() {
        return new MovingBubbleImpl(loadedBubble);
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public Bubble shoot() {
        this.vectorConv.setAngle(this.angle);
        this.loadedBubble.setSpeed(this.vectorConv.getComponents());
        final var bb2Shoot = this.loadedBubble;
        this.loadedBubble = new MovingBubbleImpl(this.bbGenerator.generate(this.startingPosition));
        return bb2Shoot;
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public void exchange() {
        // TODO Optional feature
        // TODO: Decide what to do, does the currently loaded bubble become the second
        // one or is it stored in a Tetris-like reservoir
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public int getAngle() {
        return this.angle;
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(angle, loadedBubble, startingPosition);
    }

    /***
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
        final CannonImpl other = (CannonImpl) obj;
        return angle == other.angle && loadedBubble.equals(other.loadedBubble)
                && startingPosition.equals(other.startingPosition);
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CannonImpl [angle=" + angle + ", loadedBubble=" + loadedBubble + "]";
    }

}
