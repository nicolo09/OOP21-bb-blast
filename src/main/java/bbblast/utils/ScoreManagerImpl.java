package bbblast.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bbblast.utils.persister.Persister;

public class ScoreManagerImpl implements ScoreManager {
    
    private final Persister<List<Score>> f;
    
    public ScoreManagerImpl(final Persister<List<Score>> f) {
        this.f = f;
    }
    
    @Override
    public void save(final Score s) {
        final var l = f.load().orElse(new ArrayList<>());
        l.add(s);
        try {
            f.save(l);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Score> load() {
        return f.load().orElse(new ArrayList<>());
    }

    @Override
    public void resetScores() {
        f.reset();
    }

}
