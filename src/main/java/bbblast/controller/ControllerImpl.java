package bbblast.controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

import bbblast.controller.gameloop.GameLoop;
import bbblast.controller.gameloop.GameLoopImpl;
import bbblast.controller.gameloop.Updatable;
import bbblast.model.Bubble;
import bbblast.model.Model;
import bbblast.utils.Score;
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
    private View mainView;
    private Model mainModel;
    private GameLoop loop;
    private final Persister<Settings> settingsPersister = new FilePersister<>(SETTINGSPATH, Settings.class);

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
    public void saveScore(final Score score) {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFPS() {
        return this.loop.getFPS();
    }
}
