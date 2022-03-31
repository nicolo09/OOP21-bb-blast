package bbblast.view;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameOverViewControllerImpl implements GameOverViewController {

    private final Supplier<Integer> scoreGetter;
    private final Consumer<String> scoreSaver;
    
    public GameOverViewControllerImpl(final Supplier<Integer> scoreGetter, final Consumer<String> scoreSaver) {
        this.scoreGetter = scoreGetter;
        this.scoreSaver = scoreSaver;
    }
    
    @Override
    public int getScore() {
        return scoreGetter.get();
    }

    @Override
    public void saveScore(final String name) {
        scoreSaver.accept(name);
    }

}
