package bbblast.view;

import java.util.Map;
import java.util.function.Consumer;

import bbblast.utils.Score;

public class GameOverViewControllerImpl implements GameOverViewController {

    private final Map<Integer, Integer> scores;
    private final Consumer<Score> scoreSaver;
    
    public GameOverViewControllerImpl(final Map<Integer, Integer> scores, final Consumer<Score> scoreSaver) {
        this.scores = scores;
        this.scoreSaver = scoreSaver;
    }
    
    @Override
    public int getScore() {
        return this.getMaximumScore();
    }

    @Override
    public void saveScore(final String name) {
        scoreSaver.accept(new ScoreImpl(name, this.getMaximumScore()));
    }
    
    private int getMaximumScore() {
        return this.scores.values().stream().max(Integer::compare).get();
    }

}
