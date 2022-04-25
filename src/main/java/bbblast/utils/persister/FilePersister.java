package bbblast.utils.persister;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.Optional;
/**
 * 
 * {@inheritDoc}
 * This implementation saves object on a file.
 *
 * @param <T>
 */
public class FilePersister<T> implements Persister<T> {

    private static final OpenOption[] WRITEROPTIONS = { StandardOpenOption.CREATE, StandardOpenOption.WRITE,
            StandardOpenOption.TRUNCATE_EXISTING };
    private final Path filePath;
    private final Class<T> t;

    /**
     * Creates a new FilePersister.
     * @param filePath the {@link Path} of the file to write on 
     * @param classToWrite the {@link Class} of the type parameter T
     */
    public FilePersister(final Path filePath, final Class<T> classToWrite) {
        this.filePath = filePath;
        this.t = classToWrite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<T> load() {
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(filePath))) {
            final var readObject = input.readObject();
            if (t.isInstance(readObject)) {
                return Optional.of(t.cast(readObject));
            }
            else {
                return Optional.empty();
            }
        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
            return Optional.empty();
        } 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final T objectToSave) throws IOException {
        final Path directory = filePath.getParent();
        if (Objects.nonNull(directory)) {
            Files.createDirectories(directory);
            try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(filePath, WRITEROPTIONS))) {
                output.writeObject(objectToSave);
            }            
        } else {
            throw new IOException("Invalid file path");
        }
    }

    @Override
    public boolean reset() {
        try {
            Files.delete(filePath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
