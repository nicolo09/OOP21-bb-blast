package bbblast.model.level;

import java.io.Serializable;
import java.util.Objects;

import bbblast.model.BubbleGenerator;
import bbblast.model.BubblesGrid;
import bbblast.model.BubblesGridImpl;
import bbblast.model.Cannon;
import bbblast.model.CannonImpl;
import bbblast.model.GridInfo;
import bbblast.utils.PositionImpl;

/**
 * 
 * Implementation of {@link Level}.
 *
 */
public class LevelImpl implements Level, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -868557846923024533L;
    private static final int INIT_SCORE = 0;
    private static final double CANNONVERTICALOFFSETPERCENT = 0.97;
    private static final double SPEEDMULTIPLIER = 30;
    private int score;
    private final GridInfo infos;
    private final BubblesGrid gameGrid;
    private final Cannon gameCannon;
    private final BubbleGenerator generator;

    /**
     * Creates a new Level.
     * 
     * @param infos     the {@link GridInfo} containing the informations to generate
     *                  the {@link BubblesGrid}
     * @param generator the {@link BubbleGenerator} that will be used by the
     *                  {@link BubblesGrid}
     * @param fps       number of frame per second
     */
    public LevelImpl(final GridInfo infos, final BubbleGenerator generator, final int fps) {
        this.score = INIT_SCORE;
        this.infos = infos;
        this.gameGrid = new BubblesGridImpl(infos);
        this.generator = generator;
        final var cannonPos = new PositionImpl(infos.getPointsWidth() / 2,
                infos.getPointsHeight() * CANNONVERTICALOFFSETPERCENT);
        this.gameCannon = new CannonImpl(cannonPos, fps, Math.toIntExact(Math.round(SPEEDMULTIPLIER * infos.getBubbleRadius())),
                generator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BubblesGrid getGameBubblesGrid() {
        return this.gameGrid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GridInfo getGameGridInfo() {
        return this.infos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cannon getGameCannon() {
        return this.gameCannon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentScore() {
        return this.score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateScore(final int points) {
        this.score += points;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillGameBubblesGrid(final int rows) {
        this.gameGrid.moveBubblesDown(rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < this.infos.getBubbleWidth(); j++) {
                // NOTE: these coordinates are just an estimate of what they will be inside the BubblesGrid
                final var centerX =  i % 2 == 0 
                        ? j * 2 * infos.getBubbleRadius() + infos.getBubbleRadius() 
                        : j * 2 * infos.getBubbleRadius() + 2 * infos.getBubbleRadius();
                final var centerY = i * 2 * infos.getBubbleRadius() + infos.getBubbleRadius(); // bubbles centers are distanced of about two times the radius one from the another on y axis
                this.gameGrid.addBubble(generator.generate(new PositionImpl(centerX, centerY)));
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(gameGrid, generator, infos, score, gameCannon);
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
        final LevelImpl other = (LevelImpl) obj;
        return gameGrid.equals(other.gameGrid) && generator.equals(other.generator) && infos.equals(other.infos)
                && score == other.score && gameCannon.equals(other.gameCannon);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "LevelImpl [score=" + score + ", infos=" + infos + ", gameGrid=" + gameGrid + ", gameCannon="
                + gameCannon + ", generator=" + generator + "]";
    }

}
