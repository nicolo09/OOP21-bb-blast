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
        return this.scores.values().stream().max(Integer::compare).get();
    }

    @Override
    public void saveScore(final String name) {
        //TODO: Create score and save it
        //scoreSaver.accept(name);
    }

}
