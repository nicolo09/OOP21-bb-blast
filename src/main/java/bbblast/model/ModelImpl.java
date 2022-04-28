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

    private static final double FILLHEIGHTPERCENT = 0.2;
    private MovementHandler mover;
    private Level gameLevel;
    private static final int BUBBLEVALUE = 100;
    private static final int MAXBUBBLE = 3;
    private static final int FALL = 8;
    private static final int NEWROWS = 2;
    private int counter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void startNewGame(final GridInfo grid, final int fps) {
        this.gameLevel = new LevelImpl(grid, new BubbleGeneratorImpl(COLOR.allExceptGrey()), fps);
        this.gameLevel.fillGameBubblesGrid(Math.toIntExact(Math.round(grid.getBubbleHeight() * FILLHEIGHTPERCENT)));
        this.mover = new MovementHandlerImpl(this.gameLevel.getGameBubblesGrid(), grid, a -> {
            this.scoreUpdater(a);
        });
        counter = 0;
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
            counter++;
            if(this.counter == FALL) {
               counter = 0;
               this.gameLevel.fillGameBubblesGrid(NEWROWS);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.gameLevel = null;
        mover = null;
    }
    /**
     * Update the score counting deleted bubbles.
     * @param bubble is the currently shooted bubble
     */
    private void scoreUpdater(final Bubble bubble) {
        if (this.gameLevel.getGameBubblesGrid().getSameColorNeighbors(bubble).size() > MAXBUBBLE) {
            final Collection<Bubble> bubbleSaved = this.gameLevel.getGameBubblesGrid().getSameColorNeighbors(bubble);
            this.gameLevel.updateScore(bubbleSaved.size() * BUBBLEVALUE);
            for (final Bubble b : bubbleSaved) {
                this.gameLevel.getGameBubblesGrid().removeBubble(b.getCoords());
            }
        }
        final Collection<Bubble> floatingBubbles = this.gameLevel.getGameBubblesGrid().checkForUnconnectedBubbles();
        if (!floatingBubbles.isEmpty()) {
            for (final Bubble b : floatingBubbles) {
                this.gameLevel.getGameBubblesGrid().removeBubble(b.getCoords());
                this.gameLevel.updateScore(BUBBLEVALUE);
            }
        }
    }
}
