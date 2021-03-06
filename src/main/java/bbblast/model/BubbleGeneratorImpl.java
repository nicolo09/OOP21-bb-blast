package bbblast.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;

import bbblast.utils.Position;

/**
 * 
 * Implementation of Bubble Generator.
 *
 */
public class BubbleGeneratorImpl implements BubbleGenerator, Serializable {
    private static final long serialVersionUID = -113177648690737170L;
    private final Collection<COLOR> col;
    private final Random rnd;

    /**
     * Constructor of the Bubble generator.
     * 
     * @param col is the input collection of Color
     */
    public BubbleGeneratorImpl(final Collection<COLOR> col) {
        this.col = col;
        this.rnd = new Random();
    }

    /**
     * @param p position of the bubble.
     * @return random generated bubble.
     */
    @Override
    public Bubble generate(final Position p) {
        if (col.isEmpty()) {
            return new BubbleImpl(p, COLOR.PURPLE);
        }
        final COLOR color = col.stream().skip((rnd.nextInt(col.size()))).findFirst().get();
        return new BubbleImpl(p, color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(col);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BubbleGeneratorImpl other = (BubbleGeneratorImpl) obj;
        return Objects.equals(col, other.col);
    }

}
