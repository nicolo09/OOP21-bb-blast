package bbblast.utils;

import java.io.Serializable;
import java.util.Objects;

/**
 * Implementation of {@link Settings} that can be serialized
 * ({@link Serializable})
 */
public class SettingsImpl implements Settings, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8951098145406867168L;
    private final int masterVolume;
    private final int musicVolume;
    private final int effectsVolume;

    /**
     * 
     * @param masterVolume
     * @param musicVolume
     * @param effectsVolume
     */
    public SettingsImpl(final int masterVolume, final int musicVolume, final int effectsVolume) {
        this.masterVolume = masterVolume;
        this.musicVolume = musicVolume;
        this.effectsVolume = effectsVolume;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMasterVolume() {
        return this.masterVolume;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMusicVolume() {
        return this.musicVolume;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEffectsVolume() {
        return this.effectsVolume;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(effectsVolume, masterVolume, musicVolume);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SettingsImpl other = (SettingsImpl) obj;
        return this.effectsVolume == other.effectsVolume && this.masterVolume == other.masterVolume
                && this.musicVolume == other.musicVolume;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "SettingsImpl [masterVolume=" + this.masterVolume + ", musicVolume=" + this.musicVolume
                + ", effectsVolume=" + this.effectsVolume + "]";
    }
}
