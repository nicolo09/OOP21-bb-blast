package bbblast.application;

import javafx.application.Application;

/**
 * 
 * Starting class for application.
 *
 */
public final class Launcher {

    private Launcher() {
    }

    /**
     * Main method launched on application start.
     * @param args
     */
    public static void main(final String[] args) {
        Application.launch(ViewControllerAssemblerImpl.class, args);
    }

}
