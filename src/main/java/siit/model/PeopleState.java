package siit.model;

public enum PeopleState {
    SALVAT("salvat"),NESALVAT("nesalvat");
     private String state;

    PeopleState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
