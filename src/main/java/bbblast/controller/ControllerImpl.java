package bbblast.controller;

import java.util.Collection;

import bbblast.model.Bubble;
import bbblast.model.Model;
import bbblast.utils.Settings;
import bbblast.view.View;

public class ControllerImpl implements Controller {
    
    private View mainView;
    private Model mainModel;
    private GameLoop loop = null;
    
    public ControllerImpl(){
        
    }

    @Override
    public void setView(final View v) {
        this.mainView = v;
    }

    @Override
    public void setModel(final Model m) {
        this.mainModel = m;
    }

    @Override
    public Settings loadSettings() {
        return mainModel.loadSettings();
    }

    @Override
    public void writeSettings(final Settings s) {
        mainModel.writeSettings(s);
    }

    @Override
    public void startSinglePlayerGame() {
        
    }

    @Override
    public Collection<Bubble> getBubbles() {
        return mainModel.getBubbles();
    }

    @Override
    public void moveCannon(int angle) {
        mainModel.moveCannon(angle);
    }

    @Override
    public void shootCannon() {
        mainModel.shootCannon();
    }

}
