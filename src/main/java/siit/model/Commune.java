package siit.model;


import java.util.Objects;

public class Commune {
    private int id;
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commune comuna = (Commune) o;
        return id == comuna.id && name.equals(comuna.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Commune{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
