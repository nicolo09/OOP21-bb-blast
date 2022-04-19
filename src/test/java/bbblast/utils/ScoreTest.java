package bbblast.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ScoreTest {
    
    final private ScoreImpl s1 = new ScoreImpl("lorenzo", 1000);
    final private ScoreImpl s2 = new ScoreImpl("emma", 8);
    
    @Test
    public void testscore() {
        //test 1
        assertTrue(s1.getName().equals("lorenzo"),"The two names are equal");
        assertTrue(s1.getScoreValue()==(1000),"The two score are equal");
        assertNotEquals(s1.getDate(), null, "The date exist");
        
        //test 2
        assertFalse(s2.equals(s1), "They are different");
        
        //test 3
        assertTrue(s2.equals(s2), "They are the same");
    }
    
}
