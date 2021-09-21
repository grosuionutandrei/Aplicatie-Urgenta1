package siit.model;

import java.util.List;
import java.util.Objects;

public class CommuneSettlements {
    private Commune commune;
    private List<Settlement> settlementList;

    @Override
    public String toString() {
        return "ComuneLocalitati{" +
                "commune=" + commune +
                ", settlementList=" + settlementList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommuneSettlements that = (CommuneSettlements) o;
        return commune.equals(that.commune) && settlementList.equals(that.settlementList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commune, settlementList);
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public List<Settlement> getSettlementList() {
        return settlementList;
    }

    public void setSettlementList(List<Settlement> settlementList) {
        this.settlementList = settlementList;
    }
}
