package bbblast.utils;

import java.io.IOException;
import java.util.Collection;

import bbblast.utils.persister.Persister;

/**
 * 
 * Implementation of Score manager
 *
 */

public class ScoreManagerImpl implements ScoreManager {

    private final Persister<ScoreTable> f;
    
    /**
     * Create a persister of ScoreTable
     */
    public ScoreManagerImpl(final Persister<ScoreTable> f) {
        this.f = f;
    }
    /**
     * Save a score in a Score Table
     * @param s the Score to save
     */
    @Override
    public void save(final Score s) {
        final var l = f.load().orElse(new ScoreTable());
        l.addScore(s);
        try {
            f.save(l);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Load a score from a Score Table
     * @return Collection of Scores
     */
    @Override
    public Collection<Score> load() {
        return f.load().orElse(new ScoreTable()).getList();
    }
    /**
     * Reset all the scores canceling the save file
     */
    @Override
    public void resetScores() {
        f.reset();
    }

}
