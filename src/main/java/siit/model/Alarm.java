package siit.model;
import java.util.Objects;

public class Alarm {
    private boolean value;
    private String commune;


    public Alarm(){
    }
    public Alarm(boolean value, String commune){
        this();
        this.value=value;
        this.commune=commune;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alarm alarm = (Alarm) o;
        return value == alarm.value && commune.equals(alarm.commune);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, commune);
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "value=" + value +
                ", commune='" + commune + '\''+
                '}';
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }
}
