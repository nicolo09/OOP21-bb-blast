package bbblast.view;

import java.util.function.Supplier;

public class GameOverViewControllerImpl implements GameOverViewController {

    private final Supplier<Integer> scoreGetter;
    
    public GameOverViewControllerImpl(final Supplier<Integer> scoreGetter) {
        this.scoreGetter = scoreGetter;
    }
    
    @Override
    public int getScore() {
        return scoreGetter.get();
    }

    @Override
    public void saveScore(final String name) {
        // TODO Auto-generated method stub
    }

}
