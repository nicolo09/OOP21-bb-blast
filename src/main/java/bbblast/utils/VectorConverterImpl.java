package bbblast.utils;

import java.io.Serializable;
import java.util.Objects;

/** The implementation of VectorConverter. */
public class VectorConverterImpl implements VectorConverter, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1680059491684009699L;
	private int module;
    private int angle;
    private final int fps;

    /**
     * @param fps the frames per second of the application.
     */
    public VectorConverterImpl(final int fps) {
        this.fps = fps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModule(final int module) {
        this.module = module;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAngle(final int angle) {
        this.angle = angle;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getComponents() {
        final double x = module * Math.cos(Math.toRadians(angle)) / fps;
        final double y = - module * Math.sin(Math.toRadians(angle)) / fps;
        
        return new PositionImpl(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(fps);
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
        final VectorConverterImpl other = (VectorConverterImpl) obj;
        return fps == other.fps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "VectorConverterImpl [fps=" + fps + "]";
    }

}
