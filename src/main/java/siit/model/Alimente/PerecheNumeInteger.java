package siit.model.Alimente;

import java.util.Objects;

public class PerecheNumeInteger {
    private String name;
    private int value;

    public PerecheNumeInteger() {
    }

    public PerecheNumeInteger(String name, int value) {
        this(name);
        this.value = value;
    }

    public PerecheNumeInteger(String name) {
        this();
        this.name = name;
    }

    @Override
    public String toString() {
        return "PerecheNumeInteger{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerecheNumeInteger that = (PerecheNumeInteger) o;
        return value == that.value && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
