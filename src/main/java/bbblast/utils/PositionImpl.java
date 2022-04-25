package bbblast.utils;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * Position Implementation.
 *
 */
public class PositionImpl implements Position, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3284684949009359861L;
	private double x;
	private double y;
	
	/**
	 * Returns a Position pointing to specified location.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public PositionImpl(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getX() {
		return this.x;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getY() {
		return this.y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void translate(final double dx, final double dy) {
		this.x += dx;
		this.y += dy;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCoords(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Position getCopy() {
		return new PositionImpl(this.x, this.y);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
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
		final PositionImpl other = (PositionImpl) obj;
		return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "PositionImpl [x=" + x + ", y=" + y + "]";
	}
	

}
