package bbblast.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class that memorize score in a list.
 *
 */
public class ScoreTable implements Serializable {

    /**
     * Generated serial ID.
     */
    private static final long serialVersionUID = 2214274898079476338L;
    private final List<Score> l;

    /**
     * Create an empty list.
     */
    public ScoreTable() {
        this.l = new ArrayList<Score>();
    }

    /**
     * Add a score to the list.
     * @param s
     */
    public void addScore(final Score s) {
        l.add(s);
    }

    /**
     * Return the list.
     * @return the list of scores
     */
    public List<Score> getList() {
        return new ArrayList<Score>(l);
    }
}
