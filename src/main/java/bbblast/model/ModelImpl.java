package bbblast.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import bbblast.model.level.Level;
import bbblast.model.level.LevelImpl;

/**
 * Implements a game model.
 */
public class ModelImpl implements Model {

    private MovementHandler mover;
    private Level gameLevel;

    /**
     * {@inheritDoc}
     */
    @Override
    public void startNewGame(final GridInfo grid, final int fps) {
        this.gameLevel = new LevelImpl(grid, new BubbleGeneratorImpl(COLOR.allExceptGrey()), fps);
        //TODO insert consumer
        this.mover = new MovementHandlerImpl(this.gameLevel.getGameBubblesGrid(), grid, null);
    }

    /**
     * {@inheritDoc} ticks the MovementHandler.
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
        final var result = new HashSet<>(this.gameLevel.getGameBubblesGrid().getBubbles());
        result.add(this.gameLevel.getGameCannon().getCurrentlyLoadedBubble());
        mover.getShot().ifPresent(a -> {
            result.add(a);
        });
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCannon(final int angle) {
        this.gameLevel.getGameCannon().move(angle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shootCannon() {
        if (mover.getShot().isEmpty()) {
            mover.setShot(gameLevel.getGameCannon().shoot());
        }
    }

    // TODO: Taglia fa gli score
    @Override
    public Map<Integer, Integer> getScores() {
        // TODO Auto-generated method stub
        return Map.of();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCannonAngle() {
        return this.gameLevel.getGameCannon().getAngle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLastRowReached() {
        return this.gameLevel.getGameBubblesGrid().endReached();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchBubble() {
        this.gameLevel.getGameCannon().exchange();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Level getCurrentLevel() {
        return this.gameLevel;
    }

    @Override
    public void reset() {
        this.gameLevel = null;
        mover = null;
    }
}
