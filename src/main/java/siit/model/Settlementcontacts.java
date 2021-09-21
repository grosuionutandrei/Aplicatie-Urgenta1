package siit.model;

import java.util.List;
import java.util.Objects;

public class Settlementcontacts {
    private Settlement settlement;
    private List<Contact> contacts;

    @Override
    public String toString() {
        return "Settlementcontacts{" +
                "settlement=" + settlement +
                ", contacts=" + contacts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settlementcontacts that = (Settlementcontacts) o;
        return settlement.equals(that.settlement) && contacts.equals(that.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(settlement, contacts);
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
