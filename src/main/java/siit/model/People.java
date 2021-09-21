package siit.model;
import java.time.LocalDate;
import java.util.Objects;

public class People {
 private int id;
 private String name;
 private String state;
 private int age;
 private String observatii;
 private String  image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }






    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }



    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", observatii='" + observatii + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return id == people.id && age == people.age && Objects.equals(name, people.name) && Objects.equals(state, people.state) && Objects.equals(observatii, people.observatii);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, state, observatii, age);
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAge() {
        return age;
    }

    public void setAge(LocalDate date) {
        int currentYear= LocalDate.now().getYear();
        int birthYear= date.getYear();
        this.age = currentYear-birthYear;
    }

    public void setAge1(int age){
        this.age=age;
    }

}
