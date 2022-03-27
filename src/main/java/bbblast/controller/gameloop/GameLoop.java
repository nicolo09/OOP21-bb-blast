package bbblast.controller.gameloop;

/**
 * Represents a GameLoop.
 */
public interface GameLoop {
    /**
     * Starts the loop.
     */
    void startLoop();

    /**
     * Pause the loop.
     */
    void pauseLoop();

    /**
     * Resume the loop from pause.
     */
    void resumeLoop();

    /**
     * Stops the loop.
     */
    void stopLoop();

    /**
     * Register an {@link Updatable} to be updated every {@link GameLoop} tick.
     * @param updatable
     */
    void registerUpdatable(Updatable updatable);

    /**
     * 
     * @return true if this GameLoop is paused
     */
    boolean isPaused();

    /**
     * 
     * @return true if this GameLoop is stopped
     */
    boolean isStopped();

    /**
     * 
     * @return true if this GameLoop is running (not stopped or paused)
     */
    boolean isRunning();

}