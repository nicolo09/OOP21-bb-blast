package bbblast.view.options;
/**
 * Controller for an {@link OptionView}.
 */
public interface OptionViewController {
    /**
     * 
     * @return the game master volume
     */
    int getMasterVolume();
    /**
     * 
     * @return the game music volume
     */
    int getMusicVolume();
    /**
     * 
     * @return the game effects volume
     */
    int getEffectsVolume();
    /**
     * Saves the settings.
     * @param masterVolume
     * @param musicVolume
     * @param effectsVolume
     */
    void saveSettings(double masterVolume, double musicVolume, double effectsVolume);
    /**
     * exit this view.
     */
    void exit();

}
