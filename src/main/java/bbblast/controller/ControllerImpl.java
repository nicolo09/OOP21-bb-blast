package bbblast.controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import bbblast.controller.gameloop.GameLoop;
import bbblast.controller.gameloop.GameLoopImpl;
import bbblast.controller.gameloop.Updatable;
import bbblast.model.Bubble;
import bbblast.model.Model;
import bbblast.model.RegularHexGridInfo;
import bbblast.utils.Score;
import bbblast.utils.ScoreManager;
import bbblast.utils.ScoreManagerImpl;
import bbblast.utils.ScoreTable;
import bbblast.utils.Settings;
import bbblast.utils.persister.FilePersister;
import bbblast.utils.persister.Persister;
import bbblast.view.View;
/**
 * Implementation of {@link Controller}.
 */
public class ControllerImpl implements Controller {

    //TODO Move this to a constants class
    private static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    private static final Path SETTINGSPATH = Path.of(System.getProperty("user.home") + SEPARATOR + ".bbblast" + SEPARATOR + "settings");
    private static final Path SCOREPATH = Path.of(System.getProperty("user.home") + SEPARATOR + ".bbblast" + SEPARATOR + "scores");
    private static final int BUBBLEWIDTH = 8;
    private static final int BUBBLEHEIGHT = 14;
    private static final double BUBBLEPOINTWIDTH = 100;
    private final ScoreManager scoreManager;
    private View mainView;
    private Model mainModel;
    private GameLoop loop;
    private final Persister<Settings> settingsPersister = new FilePersister<>(SETTINGSPATH, Settings.class);
    
    /**
     * creates a new Controller.
     */
    public ControllerImpl() {
        this.scoreManager = new ScoreManagerImpl(new FilePersister<>(SCOREPATH, ScoreTable.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(final View v) {
        this.mainView = v;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModel(final Model m) {
        this.mainModel = m;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Settings> loadSettings() {
        return settingsPersister.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeSettings(final Settings s) {
        try {
            settingsPersister.save(s);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startSinglePlayerGame() {
        //Gameover Handler
        final Updatable gameOverHandler = new GameOverHandlerPolling(this.mainModel, this.mainView);
        //GameLoop setup
        loop = new GameLoopImpl();
        loop.registerUpdatable(mainModel);
        loop.registerUpdatable(mainView);
        loop.registerUpdatable(gameOverHandler);
        loop.startLoop();
        //Model setup
        mainModel.startNewGame(new RegularHexGridInfo(BUBBLEWIDTH, BUBBLEHEIGHT, BUBBLEPOINTWIDTH), loop.getFPS());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pauseGame() {
        loop.pauseLoop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Bubble> getBubbles() {
        return mainModel.getBubbles();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCannon(final int angle) {
        mainModel.moveCannon(angle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shootCannon() {
        mainModel.shootCannon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Score> loadScores() {
        return scoreManager.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveScore(final Score score) {
        scoreManager.save(score);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFPS() {
        return this.loop.getFPS();
    }
}
