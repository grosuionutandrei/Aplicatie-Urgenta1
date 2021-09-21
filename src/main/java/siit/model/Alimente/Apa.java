package siit.model.Alimente;

import java.util.Objects;

public class Apa {
    private int litri;
    private String name;

    @Override
    public String toString() {
        return "Apa{" +
                "litri=" + litri +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apa apa = (Apa) o;
        return litri == apa.litri && name.equals(apa.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(litri, name);
    }
    public Apa(){

    }
    public Apa(int litri, String name) {
        this();
        this.litri = litri;
        this.name = name;
    }

    public int getLitri() {
        return litri;
    }

    public void setLitri(int litri) {
        this.litri = litri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
