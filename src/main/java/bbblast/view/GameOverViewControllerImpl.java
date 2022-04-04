package bbblast.view;

import java.util.Map;
import java.util.function.Consumer;

public class GameOverViewControllerImpl implements GameOverViewController {

    private final Map<Integer, Integer> scores;
    private final Consumer<String> scoreSaver;
    
    public GameOverViewControllerImpl(final Map<Integer, Integer> scores, final Consumer<String> scoreSaver) {
        this.scores = scores;
        this.scoreSaver = scoreSaver;
    }
    
    @Override
    public int getScore() {
        return this.scores.values().stream().max(Integer::compare).get();
    }

    @Override
    public void saveScore(final String name) {
        scoreSaver.accept(name);
    }

}
