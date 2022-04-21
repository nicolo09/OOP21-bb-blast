package bbblast.model;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum COLOR {
    
    BLUE,
    GREEN,
    ORANGE,
    PURPLE,
    RED,
    YELLOW,
    GREY;
    
    static Collection<COLOR> allExceptGrey(){
        return Stream.of(COLOR.values()).filter(a-> !a.equals(GREY)).collect(Collectors.toSet());
    }

}
