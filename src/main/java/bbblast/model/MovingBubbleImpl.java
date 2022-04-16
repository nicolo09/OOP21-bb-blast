package bbblast.model;

import bbblast.utils.Position;
import bbblast.utils.PositionImpl;

/**
 * 
 * The class which models a Bubble that can move on a 2D surface
 *
 */
public class MovingBubbleImpl extends BubbleImpl implements MovingBubble {

	private Position speed;

	/**
	 * Generates a new moving Bubble which has a velocity of 0.
	 * @param p the position of this Bubble 
	 * @param c the color of this Bubble
	 */
	public MovingBubbleImpl(final Position p,final COLOR c) {
		super(p, c);
		this.speed = new PositionImpl(0, 0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSpeed(final Position speed) {
		this.speed = speed;
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
		this.speed.setCoords( - getSpeedX(), getSpeedY());
	}

	

}
