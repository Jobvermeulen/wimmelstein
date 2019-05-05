package nl.inholland.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"brand", "model"})})
public class Guitar {

    public Guitar() {}

    @Id
    @SequenceGenerator(name="guitar_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guitar_seq")
    private long id;

    private String brand;
    private String model;
    private int price;


    public Guitar(String brand, String model, int price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Guitar(String line) {

        this (
                line.split(",")[0],
                line.split(",")[1],
                Integer.parseInt(line.split(",")[2])
        );
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Guitar{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
