package bbblast.model;

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
public class CannonImpl implements Cannon {

    private int angle = 90;
    private Bubble loadedBubble;
    // TODO: make this bubble a MovingBubble
    private final Position startingPosition;
    private final BubbleGenerator bbGenerator;
    // TODO: make this a real generator, maybe passed as a parameter by constructor
    private final VectorConverter vectorConv;

    /**
     * @param p the position at which bubbles will inially spawn.
     * 
     */
    public CannonImpl(final Position p, final int FPS, final int speed) {
        this.startingPosition = new PositionImpl(p.getX(), p.getY());
        this.bbGenerator = new BubbleGenerator();
        this.loadedBubble = bbGenerator.next(this.startingPosition);

        this.vectorConv = new VectorConverterImpl(FPS);
        this.vectorConv.setModule(speed);
    }

    /***
     * {@inheritDoc} The angle has to be between 5 and 175 degrees.
     */
    @Override
    public void move(final int angle) {
        if (angle >= 5 && angle <= 175) {
            this.angle = angle;
        }
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public Bubble getCurrentlyLoadedBubble() {
        return loadedBubble;
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public Bubble shoot() {
        // TODO: Add speed, make moving bubble
        this.vectorConv.setAngle(this.angle);
        // this.vectorConv.getComponents(); this creates the speed components
        return loadedBubble;
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

    @Override
    public int hashCode() {
        return Objects.hash(angle, loadedBubble, startingPosition);
    }

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

    @Override
    public String toString() {
        return "CannonImpl [angle=" + angle + ", loadedBubble=" + loadedBubble + "]";
    }
    
    

}
