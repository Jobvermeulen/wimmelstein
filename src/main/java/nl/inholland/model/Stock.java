package nl.inholland.model;

import javax.persistence.*;

@Entity
public class Stock {

    @Id
    @SequenceGenerator(name = "stock_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_seq")
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
