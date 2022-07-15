package bbblast.controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import bbblast.controller.gameloop.GameLoop;
import bbblast.controller.gameloop.GameLoopImpl;
import bbblast.controller.gameloop.Updatable;
import bbblast.controller.gameover.LastRowGameOverHandlerPolling;
import bbblast.model.Bubble;
import bbblast.model.GridInfo;
import bbblast.model.Model;
import bbblast.model.ModelImpl;
import bbblast.model.RegularHexGridInfo;
import bbblast.model.level.Level;
import bbblast.utils.Score;
import bbblast.utils.ScoreManager;
import bbblast.utils.ScoreManagerImpl;
import bbblast.utils.ScoreTable;
import bbblast.utils.Settings;
import bbblast.utils.persister.FilePersister;
import bbblast.utils.persister.Persister;
import bbblast.view.JFXViewImpl;
import bbblast.view.View;

/**
 * Implementation of {@link Controller}.
 */
public class ControllerImpl implements Controller {

    // TODO Move this to a constants class
    private static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    private static final Path SETTINGSPATH = Path
            .of(System.getProperty("user.home") + SEPARATOR + ".bbblast" + SEPARATOR + "settings");
    private static final Path SCOREPATH = Path
            .of(System.getProperty("user.home") + SEPARATOR + ".bbblast" + SEPARATOR + "scores");
    private static final Path LEVELPATH = Path
            .of(System.getProperty("user.home") + SEPARATOR + ".bbblast" + SEPARATOR + "level");
    private static final int BUBBLEWIDTH = 18;
    private static final int BUBBLEHEIGHT = 26;
    private static final double BUBBLEPOINTWIDTH = 100;
    private final ScoreManager scoreManager;
    private View mainView;
    private Model mainModel;
    private GameLoop loop;
    private final Persister<Settings> settingsPersister = new FilePersister<>(SETTINGSPATH, Settings.class);
    private final Persister<Level> levelPersister = new FilePersister<>(LEVELPATH, Level.class);
    private GridInfo info;

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
        // Gameover Handler
        final Updatable gameOverHandler = new LastRowGameOverHandlerPolling(this.mainModel, this.mainView, this);
        // GameLoop setup
        loop = new GameLoopImpl();
        this.info = new RegularHexGridInfo(BUBBLEWIDTH, BUBBLEHEIGHT, BUBBLEPOINTWIDTH);
        mainModel.startNewGame(this.info, loop.getFPS());
        loop.registerUpdatable(mainModel);
        loop.registerUpdatable(mainView);
        loop.registerUpdatable(gameOverHandler);
        loop.startLoop();
        // Model setup
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMultiPlayerGameAs2() {
        // Gameover Handler
        final Updatable gameOverHandler = new LastRowGameOverHandlerPolling(this.mainModel, this.mainView, this);
        // GameLoop setup
        loop = new GameLoopImpl();
        this.info = new RegularHexGridInfo(BUBBLEWIDTH, BUBBLEHEIGHT, BUBBLEPOINTWIDTH);
        mainModel.startNewGame(this.info, loop.getFPS());
        loop.registerUpdatable(mainModel);
        loop.registerUpdatable(mainView);
        loop.registerUpdatable(gameOverHandler);
        loop.startLoop();
        // Model setup
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller startMultiPlayerGameAs1(final View view) {
        final Controller controller2 = new ControllerImpl();
        controller2.setView(view);
        final Model model2 = new ModelImpl();
        this.mainModel.setOtherModel(model2);
        model2.setOtherModel(this.mainModel);
        controller2.setModel(model2);
        controller2.startMultiPlayerGameAs2();
        this.startMultiPlayerGameAs2();
        return controller2;
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
    public void resumeGame() {
        loop.resumeLoop();
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
    public void multiShootCannon() {
        mainModel.multiShootCannon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCannonAngle() {
        return mainModel.getCannonAngle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return mainModel.getCurrentLevel().getCurrentScore();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public GridInfo getGridInfo() {
        return this.info;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveLevel(final Level lvl) {
        try {
            levelPersister.save(lvl);
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
    public Optional<Level> loadLevel() {
        return levelPersister.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        if (Objects.nonNull(loop)) {
            loop.stopLoop();
            loop = null;
        }
        this.info = null;
        this.mainModel.reset();
    }


}
