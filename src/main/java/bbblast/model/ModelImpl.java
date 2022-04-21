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

public class ModelImpl implements Model {

    private static final double CANNONVERTICALOFFSETPERCENT = 0.9;
    // Set a speed
    private static final int BUBBLESPEED = 0;
    private final GridInfo gridInfo;
    private Controller controller;
    private final BubblesGrid grid;
    private final Cannon cannon;

    public ModelImpl(final GridInfo grid) {
        this.gridInfo = grid;
        this.grid = new BubblesGridImpl(grid);
        final Position bubbleSpawnPosition = new PositionImpl(gridInfo.getPointsWidth() / 2,
                gridInfo.getPointsHeight() * CANNONVERTICALOFFSETPERCENT);
        this.cannon = new CannonImpl(bubbleSpawnPosition, this.controller.getFPS(), BUBBLESPEED,
                new BubbleGeneratorImpl(COLOR.allExceptGrey()));
    }

    // TODO: Casa fa la gestione del movimento e dei rimbalzi
    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    @Override
    public Collection<Bubble> getBubbles() {
        final var result = new HashSet<>(grid.getBubbles());
        result.add(cannon.getCurrentlyLoadedBubble());
        // TODO: Add moving bubble to result
        return result;
    }

    @Override
    public void moveCannon(final int angle) {
        cannon.move(angle);
    }

    @Override
    public void shootCannon() {
        cannon.shoot();
    }

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

    @Override
    public Optional<GameOver> gameOver() {
        if (this.grid.endReached()) {
            return Optional.of(new GameOverImpl(this.getScores()));
        }
        return Optional.empty();
    }

    @Override
    public void switchBubble() {
        cannon.exchange();
    }

    // TODO: Taglia fa gli scores
    @Override
    public Collection<Score> loadScores() {
        // TODO Auto-generated method stub
        return null;
    }

    // TODO: Taglia fa gli scores
    @Override
    public void writeScore(final Score s) {
        // TODO Auto-generated method stub
    }

    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
    }

}
