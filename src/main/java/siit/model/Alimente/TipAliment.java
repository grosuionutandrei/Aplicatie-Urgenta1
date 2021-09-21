package siit.model.Alimente;

import java.util.Objects;

public class TipAliment  extends Alimente {
    private String tipAliment;
    public TipAliment(){
    }

    public TipAliment (String tipAliment,int calories, int grams,int quantity,int id) {
        super(id,calories,grams,quantity);
        this.tipAliment = tipAliment;
    }

    public TipAliment (String tipAliment,int calories,int quantity){
        super(calories,quantity);
        this.tipAliment=tipAliment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TipAliment that = (TipAliment) o;
        return tipAliment.equals(that.tipAliment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipAliment);
    }

    @Override
    public String toString() {
        return
                super.toString() +
                "tipAliment='" + tipAliment + '\'' +
                '}';
    }

    public String getTipAliment() {
        return tipAliment;
    }

    public void setTipAliment(String tipAliment) {
        this.tipAliment = tipAliment;
    }
}
