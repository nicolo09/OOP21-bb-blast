package bbblast.view.options;

import bbblast.controller.Controller;
import bbblast.utils.Settings;
import bbblast.utils.SettingsImpl;
import bbblast.view.View;

public class OptionViewControllerImpl implements OptionViewController {

    private final Controller mainController;
    private final View mainView;
    // TODO Move this to a constants class
    private static final Settings DEFAULTSETTINGS = new SettingsImpl(0, 0, 0);

    public OptionViewControllerImpl(final Controller mainController, final View mainView) {
        this.mainController = mainController;
        this.mainView = mainView;
    }

    @Override
    public int getMasterVolume() {
        return mainController.loadSettings().orElse(DEFAULTSETTINGS).getMasterVolume();
    }

    @Override
    public int getMusicVolume() {
        return mainController.loadSettings().orElse(DEFAULTSETTINGS).getMusicVolume();
    }

    @Override
    public int getEffectsVolume() {
        return mainController.loadSettings().orElse(DEFAULTSETTINGS).getEffectsVolume();
    }

    @Override
    public void exit() {
        mainView.goToMainMenu();
    }

    private int doubleToInt(final double d) {
        return Math.toIntExact(Math.round(d));
    }

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
