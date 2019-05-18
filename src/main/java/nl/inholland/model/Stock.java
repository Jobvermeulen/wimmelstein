package nl.inholland.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
public class Stock {

    @OneToOne(cascade = CascadeType.REMOVE)
    private Guitar guitar;

    @Id
    @SequenceGenerator(name = "stock_seq", initialValue = 10000001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_seq")
    private long id;

    private int quantity;
    private Stock() {}

    public Stock(Guitar guitar, int quantity) {
        this.guitar = guitar;
        this.quantity = quantity;
    }

    public Guitar getGuitar() {
        return guitar;
    }

    public void setGuitar(Guitar guitar) {
        this.guitar = guitar;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getId() {
        return id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", guitar=" + guitar +
                ", quantity=" + quantity +
                '}';
    }
}
