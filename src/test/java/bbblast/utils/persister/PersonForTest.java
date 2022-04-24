package bbblast.utils.persister;

import java.io.Serializable;
import java.util.Objects;

/**
 * Generic person class to test persistance layer independently from other
 * components.
 */
public class PersonForTest implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5552387880392078106L;
    private final String name;
    private final String surname;
    private final int age;

    /**
     * 
     */
    public PersonForTest() {
        this.name = null;
        this.surname = null;
        this.age = 0;
    }

    /**
     * 
     * @param name
     * @param surname
     * @param age
     */
    public PersonForTest(final String name, final String surname, final int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(age, name, surname);
    }

    /**
     * 
     * @return this person name
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return this person surname
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * 
     * @return this person age
     */
    public int getAge() {
        return this.age;
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
        final PersonForTest other = (PersonForTest) obj;
        return this.age == other.age && Objects.equals(this.name, other.name)
                && Objects.equals(this.surname, other.surname);
    }
}
