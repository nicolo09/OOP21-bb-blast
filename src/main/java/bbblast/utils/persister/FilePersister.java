package bbblast.utils.persister;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FilePersister<T> implements Persister<T> {

    private final Path filePath;
    private static final OpenOption[] WRITEROPTIONS = { StandardOpenOption.CREATE, StandardOpenOption.WRITE,
            StandardOpenOption.TRUNCATE_EXISTING };
    private final Class<T> t;

    public FilePersister(final Path filePath, final Class<T> classToWrite) {
        this.filePath = filePath;
        this.t = classToWrite;
    }
    
    @Override
    public T load() {
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(filePath))) {
            final var readObject = input.readObject();
            if (t.isInstance(readObject)) {
                return (T) readObject;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(final T objectToSave) {
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(filePath, WRITEROPTIONS))) {
            output.writeObject(objectToSave);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
