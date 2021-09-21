package siit.model.Settlementview;

import java.util.Objects;

public class UserSettlementView {
    private int idSettlement;
    private String settlement;
    private int idCommune;
    private String commune;

    @Override
    public String toString() {
        return "UserSettlementView{" +
                "id=" + idSettlement +
                ", name='" + settlement + '\'' +
                ", idCommune=" + idCommune +
                ", commune='" + commune + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSettlementView that = (UserSettlementView) o;
        return idSettlement == that.idSettlement && idCommune == that.idCommune && settlement.equals(that.settlement) && commune.equals(that.commune);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSettlement, settlement, idCommune, commune);
    }

    public int getIdSettlement() {
        return idSettlement;
    }

    public void setIdSettlement(int idSettlement) {
        this.idSettlement = idSettlement;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public int getIdCommune() {
        return idCommune;
    }

    public void setIdCommune(int idCommune) {
        this.idCommune = idCommune;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }
}
