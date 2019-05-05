package nl.inholland.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stock {

    @Id
    @GeneratedValue
    private long id;

    private long guitarId;
    private int quantity;

    public Stock() {}

    public Stock(long guitarId, int quantity) {
        this.guitarId = guitarId;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public long getGuitarId() {
        return guitarId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", guitarId=" + guitarId +
                ", quantity=" + quantity +
                '}';
    }
}
