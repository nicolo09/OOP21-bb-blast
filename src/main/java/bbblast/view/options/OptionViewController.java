package bbblast.view.options;

public interface OptionViewController {
    
    int getMasterVolume();
    
    int getMusicVolume();
    
    int getEffectsVolume();
    
    void saveSettings(double masterVolume, double musicVolume, double effectsVolume);
    
    void exit();

}
