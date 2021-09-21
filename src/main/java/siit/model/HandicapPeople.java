package siit.model;

public class HandicapPeople extends People{
    private String type;
    private int localnicId;

    public int getLocalnicId() {
        return localnicId;
    }

    public void setLocalnicId(int localnicId) {
        this.localnicId = localnicId;
    }

    public HandicapPeople() {
        super();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "HandicapPeople{" +
                "id='" + super.getId() + '\'' +
                "name='" + super.getName() + '\'' +
                "state='" + super.getState() + '\'' +
                "age='" + super.getAge() + '\'' +
                "observatii='" + super.getObservatii() + '\'' +
                "type='" + type + '\'' +
                "localnic_id='" + localnicId + '\'' +
                '}';
    }
}
