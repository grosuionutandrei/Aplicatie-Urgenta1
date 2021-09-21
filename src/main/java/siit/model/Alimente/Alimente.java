package siit.model.Alimente;


import java.util.Objects;

public abstract class Alimente {
    private int id;
    private int calories;
    private int grams;
    private int quantity;

    public Alimente() {
    }

    public Alimente(int calories, int grams, int quantity, int id) {
        this();
        this.calories = calories;
        this.grams = grams;
        this.quantity = quantity;
        this.id = id;
    }

    public Alimente(int calories, int quantity) {
        this.calories = calories;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Alimente{" +
                "id=" + id +
                ", calories=" + calories +
                ", grams=" + grams +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alimente alimente = (Alimente) o;
        return id == alimente.id && calories == alimente.calories && grams == alimente.grams && quantity == alimente.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calories, grams, quantity);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalories() {
        return calories;
    }


    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
