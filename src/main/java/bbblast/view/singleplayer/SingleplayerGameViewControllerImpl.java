package bbblast.view.singleplayer;

import java.util.Collection;

import bbblast.controller.Controller;
import bbblast.model.Bubble;
/**
 * Implements {@link SingleplayerGameViewController}.
 */
public class SingleplayerGameViewControllerImpl implements SingleplayerGameViewController {

    private static final int ANGLETOMOVE = 3;
    private final Controller controller;
    /**
     * 
     * @param controller the main application controller
     */
    public SingleplayerGameViewControllerImpl(final Controller controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Bubble> getBubbles() {
        return controller.getBubbles();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCannon(final int angle) {
        controller.moveCannon(angle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cannonShoot() {
        controller.shootCannon();
    }
    
    /**
     * {@inheritDoc}
     */
    public void multiCannonShoot() {
        controller.multiShootCannon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return controller.getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() {
        controller.pauseGame();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void resume() {
        controller.resumeGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        controller.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCannonAngle() {
        return controller.getCannonAngle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCannonRight() {
        this.moveCannon(controller.getCannonAngle() - ANGLETOMOVE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCannonLeft() {
        this.moveCannon(controller.getCannonAngle() + ANGLETOMOVE);
    }

}
