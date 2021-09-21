package siit.model;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class PeopleAdapter {
    private String name;
    private String state;
    private String observatii;
    private String date;
    private Settlement settlement;
    private String map;
    private String mijlocDeAcces;
    private String tipHandicap;
    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public String getTipHandicap() {
        return tipHandicap;
    }

    public void setTipHandicap(String tipHandicap) {
        this.tipHandicap = tipHandicap;
    }


    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
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

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
