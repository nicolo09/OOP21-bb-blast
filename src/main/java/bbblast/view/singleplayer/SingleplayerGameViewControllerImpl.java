package bbblast.view.singleplayer;

import java.util.Collection;

import bbblast.controller.Controller;
import bbblast.model.Bubble;

public class SingleplayerGameViewControllerImpl implements SingleplayerGameViewController {

    private static final int ANGLETOMOVE = 2;
    private final Controller controller;

    public SingleplayerGameViewControllerImpl(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public Collection<Bubble> getBubbles() {
        return controller.getBubbles();
    }

    @Override
    public void moveCannon(final int angle) {
        controller.moveCannon(angle);
    }

    @Override
    public void cannonShoot() {
        controller.shootCannon();
    }

    @Override
    public int getScore() {
        return controller.getScore();
    }

    @Override
    public void pause() {
        controller.pauseGame();
    }

    @Override
    public void exit() {
        controller.reset();
    }

    @Override
    public int getCannonAngle() {
        return controller.getCannonAngle();
    }

    @Override
    public void moveCannonRight() {
        this.moveCannon(controller.getCannonAngle() - ANGLETOMOVE);
    }

    @Override
    public void moveCannonLeft() {
        this.moveCannon(controller.getCannonAngle() + ANGLETOMOVE);
    }

}
