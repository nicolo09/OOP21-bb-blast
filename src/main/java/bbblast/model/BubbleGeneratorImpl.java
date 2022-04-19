package bbblast.model;

import java.util.Collection;
import java.util.Random;

import bbblast.utils.Position;
/**
 * 
 * Implementation of Bubble Generator.
 *
 */
public class BubbleGeneratorImpl implements BubbleGenerator {
    
    private final Collection<COLOR> col;
    private final Random rnd;
    /**
     * Costructor of the Bubble generator.
     * @param col.
     */
    public BubbleGeneratorImpl (final Collection<COLOR> col) {
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

}