package bbblast.model;

import java.util.Collection;
import java.util.Map;

import bbblast.controller.Controller;
import bbblast.utils.Score;
import bbblast.utils.Settings;

public class ModelImpl implements Model {

    private final GridInfo gridInfo;
    private Controller controller;
    
    public ModelImpl(final GridInfo grid) {
        this.gridInfo = grid;
    }
    
    //TODO: Casa fa la gestione del movimento e dei rimbalzi
    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    @Override
    public Collection<Bubble> getBubbles() {
        // TODO Auto-generated method stub
        return null;
    }

    //TODO: Emma fa il cannone
    @Override
    public void moveCannon(final int angle) {
        // TODO Auto-generated method stub
    }

    //TODO: Emma fa il cannone
    @Override
    public void shootCannon() {
        // TODO Auto-generated method stub
    }

    //TODO: Emma fa il cannone
    @Override
    public int getCannonAngle() {
        // TODO Auto-generated method stub
        return 0;
    }

    //TODO: Taglia fa gli score
    @Override
    public Map<Integer, Integer> getScores() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub
        return false;
    }

    //TODO: Emma fa il cannone
    @Override
    public void switchBubble() {
        // TODO Auto-generated method stub

    }

    //TODO: Taglia fa gli scores
    @Override
    public Collection<Score> loadScores() {
        // TODO Auto-generated method stub
        return null;
    }

    //TODO: Taglia fa gli scores
    @Override
    public void writeScore(final Score s) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
    }

}
