package bbblast.model;

import bbblast.utils.Position;
import bbblast.utils.PositionImpl;

public class MovementHandlerImpl implements MovementHandler {

	private final BubblesGrid grid;
	private final GridInfo infos;
	private MovingBubble shot;
	private final double shotRadius;
	
	public MovementHandlerImpl(final BubblesGrid grid, final GridInfo infos) {
		this.grid = grid;
		this.infos = infos;
		this.shotRadius = infos.getPointsWidth()/infos.getBubbleWidth()/2;
	}
	
	@Override
	public boolean handle() {
		//the MovementHandler can't handle a non existing MovingBubble
		if(shot != null) {			
			return false;
		}
		//if it's attachable it adds the shot to the grid and deletes it
		if(grid.isBubbleAttachable(this.shot.getStationaryCopy())) {
			grid.addBubble(shot.getStationaryCopy());
			this.shot = null;
			return false;
		}
		
		boolean fixed = false;
		final var nextPos = getNextPos(shot);
		if(nextPos.getX() < 0 + shotRadius 
				|| nextPos.getX() > infos.getPointsWidth() - shotRadius) {
			fixMovement(shot, nextPos);
			fixed = true;
		}
		
		if(!fixed) {
			this.shot.move();
		}
		
		return true;
	}

	@Override
	public void setShot(final MovingBubble shot) {
		this.shot = shot;
	}
	
	private Position getNextPos(final MovingBubble shot) {
		final var copy = shot.getCoords();
		copy.translate(shot.getSpeedX(), shot.getSpeedY());
		return copy;
	}
	
	private void fixMovement(final MovingBubble shot, final Position nextPos) {
		//the needed space to reach the wall
		final var limitX = shot.getSpeedX() > 0
				? infos.getPointsWidth() - shotRadius - shot.getCoords().getX()
				: - shot.getCoords().getX() + shotRadius;
		//the remaining space that the shot has to move 
		final var correctX = shot.getSpeedX() > 0 
				? infos.getPointsWidth() - shotRadius - nextPos.getX() 
				: - nextPos.getX() + shotRadius;
		
		shot.moveBy(new PositionImpl(limitX + correctX, 0));
		//after reaching the correct position its direction changes
		shot.swapSpeedX();
	}

}
