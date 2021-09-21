package siit.model;

import java.util.Objects;

public class ChartValues {
    private String key;
    private double value;
    public ChartValues(){

    }

    public ChartValues(String key, double value) {
        this();
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "ChartValues{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChartValues that = (ChartValues) o;
        return Double.compare(that.value, value) == 0 && key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
