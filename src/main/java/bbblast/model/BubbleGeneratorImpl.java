package bbblast.model;

import java.util.Collection;
import java.util.Random;

import bbblast.utils.Position;

public class BubbleGeneratorImpl implements BubbleGenerator {
    
    private final Collection<COLOR> col;
    private final Random rnd;

    public BubbleGeneratorImpl (final Collection<COLOR> col) {
        this.col = col;
        this.rnd = new Random();
    }
    
    @Override
    public Bubble generate(Position p) {
        
        if (col.isEmpty()) {
            return new BubbleImpl(p, COLOR.PURPLE);
        }
        
        COLOR color = col.stream().skip((rnd.nextInt(col.size()))).findFirst().get();
        
        return new BubbleImpl(p, color);
    }

}
