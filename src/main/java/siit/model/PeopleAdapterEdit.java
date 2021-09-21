package siit.model;

public class PeopleAdapterEdit {
    private int id;
    private String name;
    private String Observatii;
    private String mijlocDeAcces;
    private String tipHandicap;
    private String birthDate;
    private String localitate;
    private String state;

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getLocalitate() {
        return localitate;
    }

    public void setLocalitate(String localitate) {
        this.localitate = localitate;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObservatii() {
        return Observatii;
    }

    public void setObservatii(String observatii) {
        Observatii = observatii;
    }

    public String getMijlocDeAcces() {
        return mijlocDeAcces;
    }

    public void setMijlocDeAcces(String mijlocDeAcces) {
        this.mijlocDeAcces = mijlocDeAcces;
    }

    @Override
    public String toString() {
        return "PeopleAdapterEdit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Observatii='" + Observatii + '\'' +
                ", mijlocDeAcces='" + mijlocDeAcces + '\'' +
                ", tipHandicap='" + tipHandicap + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", localitate='" + localitate + '\'' +
                '}';
    }

    public String getTipHandicap() {
        return tipHandicap;
    }

    public void setTipHandicap(String tipHandicap) {
        this.tipHandicap = tipHandicap;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
