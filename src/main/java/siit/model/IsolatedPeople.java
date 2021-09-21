package siit.model;

public class IsolatedPeople extends People {
    private String map;
    private String mijlocDeAcces;
    private int localnicId;

    public int getLocalnicId() {
        return localnicId;
    }

    public void setLocalnicId(int localnicId) {
        this.localnicId = localnicId;
    }

    public IsolatedPeople() {
        super();
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getMijlocDeAcces() {
        return mijlocDeAcces;
    }

    public void setMijlocDeAcces(String mijlocDeAcces) {
        this.mijlocDeAcces = mijlocDeAcces;
    }

    @Override
    public String toString() {
        return "IsolatedPeople{" +
                "id= ' " + super.getId() + '\'' +
                "name= ' " + super.getName() + '\'' +
                "state= '" + super.getState() + '\'' +
                "age=  ' " + super.getAge() + '\'' +
                "observatii= '" + super.getObservatii() + '\'' +
                "map='" + map + '\'' +
                ", mijlocDeAcces='" + mijlocDeAcces + '\'' +
                '}';
    }
}
