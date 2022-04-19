package bbblast.utils.persister;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import bbblast.utils.Settings;
import bbblast.utils.SettingsImpl;

public class TestPersister {

    private static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    private static final Path PATH = Path
            .of(System.getProperty("user.home") + SEPARATOR + ".bbblast-test" + SEPARATOR + "test1.json");

    @Test
    public void testFilePersister() {
        final PersonForTest objectToSave = new PersonForTest("Marco", "Rossi", 24);
        final Persister<PersonForTest> persister = new FilePersister<>(PATH, PersonForTest.class);
        persister.save(objectToSave);
        final var loaded = persister.load();
        assertEquals(loaded, objectToSave, "Loaded object is different");
    }
    
    @Test
    public void testSettingsPersister() {
        final Settings objectToSave = new SettingsImpl(100,75,50);
        final Persister<Settings> persister = new FilePersister<>(PATH, Settings.class);
        persister.save(objectToSave);
        final var loaded = persister.load();
        assertEquals(loaded, objectToSave, "Loaded settings are different");
    }

}
