package bbblast.utils;

/**
 * 
 * Position Implementation.
 *
 */
public class PositionImpl implements Position {
	
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

}
