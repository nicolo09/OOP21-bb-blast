package bbblast.view.singleplayer;

import java.util.Collection;
import java.util.List;

import bbblast.model.Bubble;
import bbblast.model.BubbleGeneratorImpl;
import bbblast.model.COLOR;
import bbblast.model.Cannon;
import bbblast.model.CannonImpl;

public class SingleplayerGameViewControllerImpl implements SingleplayerGameViewController {
    
    private final int a = CannonImpl.START_ANGLE;
    private final Cannon c = new CannonImpl(null, 60, 60, new BubbleGeneratorImpl(List.of(COLOR.BLUE, COLOR.GREEN, COLOR.RED)));

    @Override
    public Collection<Bubble> getBubbles() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void cannonShoot() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getScore() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void exit() {
        // TODO Auto-generated method stub
        
    }

}
