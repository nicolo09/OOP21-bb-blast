package bbblast.utils.persister;

/**
 * Interface that represents an object that can save and load a file.
 */
public interface Persister <T> {
        
    /**
     * loads an object.
     * @return the object
     */
    T load();

    void save(T objectToSave);

}
