package bbblast.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import bbblast.utils.PositionImpl;

public class BubbleGeneratorTest {
    
    private final BubbleGeneratorImpl b1 = new BubbleGeneratorImpl(List.of(COLOR.GREEN,
                                                             COLOR.BLUE, 
                                                             COLOR.ORANGE, 
                                                             COLOR.PURPLE, 
                                                             COLOR.RED, 
                                                             COLOR.YELLOW));
    
    @Test
    public void bubbletest() {
        //test 1
        assertNotEquals(b1.generate(new PositionImpl(0, 0)), null, "They are different");
        
        //test 2
        assertEquals(b1.generate(new PositionImpl(1, 2)).getCoords(), new PositionImpl(1, 2), "They are the same");
    }

}
