package bbblast.utils;

public class SettingsImpl implements Settings{

    private final int masterVolume;
    private final int musicVolume;
    private final int effectsVolume;
    
    public SettingsImpl(final int masterVolume, final int musicVolume, final int effectsVolume){
        this.masterVolume = masterVolume;
        this.musicVolume = musicVolume;
        this.effectsVolume = effectsVolume;
    }
    
    @Override
    public int getMasterVolume() {
        return this.masterVolume;
    }

    @Override
    public int getMusicVolume() {
        return this.musicVolume;
    }

    @Override
    public int getEffectsVolume() {
        return this.effectsVolume;
    }

}
