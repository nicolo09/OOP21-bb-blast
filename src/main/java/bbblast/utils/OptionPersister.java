package bbblast.utils;

/**
 * Interface that model the Option Persister.
 */
public interface OptionPersister {
    /**
     * save the current settings.
     * @param s the current settings
     */
    void save(Settings s);
    
    /**
     * read the settings from a file.
     * @return the settings
     */
    
    Settings load();
}
