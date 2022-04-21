package bbblast.utils.persister;

import java.io.IOException;
import java.util.Optional;

/**
 * Interface that represents an object that can save and load an object.
 */
public interface Persister <T> {
        
    /**
     * loads an object.
     * @return an optional containing the object if it has been loaded, {@link Optional.empty} otherwise
     */
    Optional<T> load();

    /**
     * Saves an object
     * @param objectToSave the object to save
     * @throws IOException if an IO error occurs on saving
     */
    void save(T objectToSave) throws IOException;

    /**
     * deletes the object persistance
     * @return true if successfully deleted
     */
    boolean reset();
}
