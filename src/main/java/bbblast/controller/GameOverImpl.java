package bbblast.controller;

import java.time.LocalDateTime;
import java.util.Map;

public class GameOverImpl implements GameOver {

    private final LocalDateTime timestamp;
    
    public GameOverImpl() {
        this.timestamp = LocalDateTime.now();
        
    }
    
    @Override
    public GAME getGameType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<Integer, Integer> scores() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LocalDateTime timestamp() {
        // TODO Auto-generated method stub
        return null;
    }

}
