package bbblast.controller.gameloop;

import java.util.ArrayList;
import java.util.List;

public class GameLoopImpl extends Thread implements GameLoop {
    private static final int FPS = 60;
    private static final int SECOND = 1000;
    private static final int TICKDURATION = SECOND / FPS;
    private volatile boolean stopped;
    private volatile boolean paused;
    private final List<Updatable> updatableList;

    public GameLoopImpl() {
        updatableList = new ArrayList<>();
        stopped = true;
        paused = false;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (!this.isStopped()) {
            start = System.currentTimeMillis();
            if (!this.isPaused()) {
                update();
            }
            try {
                sleep(start + TICKDURATION - System.currentTimeMillis());
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void registerUpdatable(final Updatable updatable) {
        this.updatableList.add(updatable);
    }

    @Override
    public synchronized void startLoop() {
        stopped = false;
        this.start();
    }

    @Override
    public synchronized void pauseLoop() {
        this.paused = true;
    }

    @Override
    public synchronized void resumeLoop() {
        this.paused = false;
    }

    @Override
    public synchronized void stopLoop() {
        this.stopped = true;
    }

    private void update() {
        this.updatableList.forEach(a -> a.update());
    }

    @Override
    public synchronized boolean isPaused() {
        return this.paused;
    }

    @Override
    public synchronized boolean isStopped() {
        return this.stopped;
    }

    @Override
    public synchronized boolean isRunning() {
        return !this.isStopped() && !this.isPaused();
    }

    @Override
    public int getFPS() {
        return FPS;
    }

}
