package bbblast.view;

import java.util.Map;
import java.util.function.Consumer;

import bbblast.utils.Score;
import bbblast.utils.ScoreImpl;
/**
 * Implements a {@link GameOverViewController} showing a dialog to save the score in leaderboard.
 */
public class GameOverViewControllerImpl implements GameOverViewController {

    private final Map<Integer, Integer> scores;
    private final Consumer<Score> scoreSaver;
    /**
     * 
     * @param scores the scores of this game
     * @param scoreSaver a {@link Consumer} that saves a score
     */
    public GameOverViewControllerImpl(final Map<Integer, Integer> scores, final Consumer<Score> scoreSaver) {
        this.scores = scores;
        this.scoreSaver = scoreSaver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.getMaximumScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveScore(final String name) {
        scoreSaver.accept(new ScoreImpl(name, this.getMaximumScore()));
    }

    /**
     * 
     * @return the maximum score of this game
     */
    private int getMaximumScore() {
        return this.scores.values().stream().max(Integer::compare).orElseGet(() -> 0);
    }

}
