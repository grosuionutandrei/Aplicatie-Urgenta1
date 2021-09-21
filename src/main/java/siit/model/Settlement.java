package siit.model;
import java.util.Objects;

public class Settlement {
    private int id;
    private String name;
    private float latitude;
    private float longitude;

    @Override
    public String toString() {
        return "Settlement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settlement that = (Settlement) o;
        return id == that.id && Float.compare(that.latitude, latitude) == 0 && Float.compare(that.longitude, longitude) == 0 && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, latitude, longitude);
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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
