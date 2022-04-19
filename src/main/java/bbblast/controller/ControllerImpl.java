package bbblast.controller;

import java.util.Collection;

import bbblast.controller.gameloop.GameLoop;
import bbblast.controller.gameloop.GameLoopImpl;
import bbblast.controller.gameloop.Updatable;
import bbblast.model.Bubble;
import bbblast.model.Model;
import bbblast.utils.Score;
import bbblast.utils.Settings;
import bbblast.view.View;

public class ControllerImpl implements Controller {
    
    private View mainView;
    private Model mainModel;
    private GameLoop loop = null;
    
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
        //View setup
        mainView.startSinglePlayerGame();
        //Model setup
        
        //Gameover Handler
        final Updatable gameOverHandler = new GameOverHandlerPolling(this.mainModel, this.mainView);
        //GameLoop setup
        loop = new GameLoopImpl();
        loop.registerUpdatable(mainModel);
        loop.registerUpdatable(mainView);
        loop.registerUpdatable(gameOverHandler);
        loop.startLoop();
    }

    @Override
    public void pauseGame() {
        loop.pauseLoop();
    }
    
    @Override
    public Collection<Bubble> getBubbles() {
        return mainModel.getBubbles();
    }

    @Override
    public void moveCannon(final int angle) {
        mainModel.moveCannon(angle);
    }

    @Override
    public void shootCannon() {
        mainModel.shootCannon();
    }

    @Override
    public int getScore() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void saveScore(final Score score) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getFPS() {
        return this.loop.getFPS();
    }


}
