package bbblast.model;

import bbblast.utils.Position;
import bbblast.utils.PositionImpl;
/**
 * 
 * Implementation of MovementHandler.
 *
 */
public class MovementHandlerImpl implements MovementHandler {

	private final BubblesGrid grid;
	private final GridInfo infos;
	private final double shotRadius;
	private boolean isShotSet;
	private MovingBubble shot;

	/**
	 * Creates a new MovementHandler.
	 * @param grid where the shot will be eventually attached to
	 * @param infos the informations about the grid
	 */
	public MovementHandlerImpl(final BubblesGrid grid, final GridInfo infos) {
		this.grid = grid;
		this.infos = infos;
		this.isShotSet = false;
		this.shotRadius = infos.getPointsWidth() / infos.getBubbleWidth() / 2;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean handle() {
		// the MovementHandler can't handle a non existing MovingBubble
		if (!isShotSet) {
			return false;
		}
		// if the Bubble is attachable it adds the shot to the grid and deletes it
		if (grid.isBubbleAttachable(this.shot.getStationaryCopy())) {
			grid.addBubble(shot.getStationaryCopy());
			this.shot = null;
			this.isShotSet = false;
			return false;
		}
		// the MovementHandler can't handle a MovingBubble with positive Y velocity
		if(this.shot.getSpeedY() > 0) {
			return false;
		}
		boolean fixedX = false;
		boolean fixedY = false;
		final var nextPos = getNextPos(shot);
		if (nextPos.getX() < shotRadius || nextPos.getX() > infos.getPointsWidth() - shotRadius) {
			fixMovement(shot, nextPos);
			fixedX = true;
		}
		if(nextPos.getY() < shotRadius) {
			this.shot.moveBy(new PositionImpl(0, -shot.getCoords().getY() + shotRadius));
			fixedY = true;
		}

		if(fixedX && !fixedY) {
			shot.moveBy(new PositionImpl(0, shot.getSpeedY()));
		} else if(!fixedX && fixedY) {
			shot.moveBy(new PositionImpl(shot.getSpeedX(), 0));
		} else if(!fixedX && !fixedY){
			shot.move();
		}

		return true;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setShot(final MovingBubble shot) {
		this.isShotSet = true;
		this.shot = shot;
	}

	private Position getNextPos(final MovingBubble shot) {
		final var copy = shot.getCoords();
		copy.translate(shot.getSpeedX(), shot.getSpeedY());
		return copy;
	}

	private void fixMovement(final MovingBubble shot, final Position nextPos) {
		// the needed space to reach the wall
		final var limitX = shot.getSpeedX() > 0 ? infos.getPointsWidth() - shotRadius - shot.getCoords().getX()
				: -shot.getCoords().getX() + shotRadius;
		// the remaining space that the shot has to move
		final var correctX = shot.getSpeedX() > 0 ? infos.getPointsWidth() - shotRadius - nextPos.getX()
				: -nextPos.getX() + shotRadius;

		shot.moveBy(new PositionImpl(limitX + correctX, shot.getSpeedY()));
		// after reaching the correct position its direction changes
		shot.swapSpeedX();
	}

	@Override
	public String toString() {
		return isShotSet ? "MovementHandlerImpl [shot=" + shot + "]" : "MovementHandlerImpl [there's no shot to handle]";
	}

	
}
