package bbblast.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import bbblast.controller.GameOver;
import bbblast.controller.GameOverImpl;
import bbblast.controller.Controller;
import bbblast.utils.Position;
import bbblast.utils.PositionImpl;
import bbblast.utils.Score;

/**
 * Implements a game model.
 */
public class ModelImpl implements Model {

    private static final double CANNONVERTICALOFFSETPERCENT = 0.9;
    // TODO: Set a speed
    private static final int BUBBLESPEED = 0;
    
    private BubblesGrid grid;
    private Cannon cannon;
    private MovementHandler mover;

    /**
     * {@inheritDoc}
     */
    @Override
    public void startNewGame(final GridInfo grid, final int fps) {
        this.grid = new BubblesGridImpl(grid);
        final Position bubbleSpawnPosition = new PositionImpl(grid.getPointsWidth() / 2,
                grid.getPointsHeight() * CANNONVERTICALOFFSETPERCENT);
        this.cannon = new CannonImpl(bubbleSpawnPosition, fps, BUBBLESPEED,
                new BubbleGeneratorImpl(COLOR.allExceptGrey()));
        this.mover = new MovementHandlerImpl(this.grid, grid);
    }
    
    /**
     * {@inheritDoc}
     * ticks the MovementHandler.
     */
    @Override
    public void update() {
        mover.handle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Bubble> getBubbles() {
        final var result = new HashSet<>(grid.getBubbles());
        result.add(cannon.getCurrentlyLoadedBubble());
        // TODO: Add moving bubble to result
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCannon(final int angle) {
        cannon.move(angle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shootCannon() {
        cannon.shoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCannonAngle() {
        return cannon.getAngle();
    }

    // TODO: Taglia fa gli score
    @Override
    public Map<Integer, Integer> getScores() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameOver> gameOver() {
        if (this.grid.endReached()) {
            return Optional.of(new GameOverImpl(this.getScores()));
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchBubble() {
        cannon.exchange();
    }

}
