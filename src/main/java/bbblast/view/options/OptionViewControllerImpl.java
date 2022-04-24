package bbblast.view.options;

import bbblast.controller.Controller;
import bbblast.utils.Settings;
import bbblast.utils.SettingsImpl;
import bbblast.view.View;

/**
 * Implements an OptionViewController.
 */
public class OptionViewControllerImpl implements OptionViewController {

    private final Controller mainController;
    private final View mainView;
    // TODO Move this to a constants class
    private static final Settings DEFAULTSETTINGS = new SettingsImpl(0, 0, 0);

    /**
     * 
     * @param mainController the main application controller
     * @param mainView       the main application view
     */
    public OptionViewControllerImpl(final Controller mainController, final View mainView) {
        this.mainController = mainController;
        this.mainView = mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMasterVolume() {
        return mainController.loadSettings().orElse(DEFAULTSETTINGS).getMasterVolume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMusicVolume() {
        return mainController.loadSettings().orElse(DEFAULTSETTINGS).getMusicVolume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEffectsVolume() {
        return mainController.loadSettings().orElse(DEFAULTSETTINGS).getEffectsVolume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        mainView.goToMainMenu();
    }

    /**
     * Converts a double value to an int.
     * @param d
     * @return the converted value
     */
    private int doubleToInt(final double d) {
        return Math.toIntExact(Math.round(d));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveSettings(final double masterVolume, final double musicVolume, final double effectsVolume) {
        if (mainController.writeSettings(
                new SettingsImpl(doubleToInt(masterVolume), doubleToInt(musicVolume), doubleToInt(effectsVolume)))) {
            this.exit();
        } else {
            mainView.showError("Non è stato possibile salvare le impostazioni");
        }
    }

}
