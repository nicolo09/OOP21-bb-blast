package bbblast.controller.gameloop;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class GameLoopTest {

    private class UpdatableForTest implements Updatable {

        private boolean updated = false;

        @Override
        public void update() {
            this.updated = true;
        }

        public boolean isUpdated() {
            return this.updated;
        }

        public void reset() {
            this.updated = false;
        }

    }

    @Test
    public void testGameLoopImplMethods() {
        final GameLoop loop = new GameLoopImpl();
        final UpdatableForTest updatable = new UpdatableForTest();
        assertTrue(loop.isStopped(), "Loop is not stopped at the creation");
        assertFalse(loop.isPaused(), "Loop is paused at the creation");
        assertFalse(loop.isRunning(), "Loop is running at the beginning");
        loop.registerUpdatable(updatable);
        assertFalse(updatable.isUpdated(), "Loop updates Updatable before running");
        loop.startLoop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail("Interrupted");
        }
        assertTrue(loop.isRunning(), "Loop says he's not running after start");
        assertTrue(updatable.isUpdated(), "Loop hasn't updated Updatable after 1 second");
        loop.pauseLoop();
        assertTrue(loop.isPaused(), "Loop says he's not paused");
        assertFalse(loop.isRunning(), "Loop says he's running on pause");
        updatable.reset();
        assertFalse(updatable.isUpdated(), "Loop updates Updatable when paused");
        loop.resumeLoop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail("Interrupted");
        }
        assertTrue(updatable.isUpdated(), "Loop doesn't update Updatable when resumed");
        assertTrue(loop.isRunning(), "Loop says he's not running after resume");
    }
    
}
