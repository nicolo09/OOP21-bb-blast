package bbblast.utils.persister;

import java.io.Serializable;
import java.util.Objects;

public class PersonForTest implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5552387880392078106L;
    private final String name;
    private final String surname;
    private final int age;

    public PersonForTest() {
        this.name = null;
        this.surname = null;
        this.age = 0;
    }

    public PersonForTest(final String name, final String surname, final int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, surname);
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getAge() {
        return this.age;
    }

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