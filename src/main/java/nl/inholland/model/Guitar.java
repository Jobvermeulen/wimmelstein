package nl.inholland.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"brand", "model"})})
public class Guitar {

    @OneToOne
    private Stock stock;

    @Id
    @SequenceGenerator(name="guitar_seq", initialValue = 1000001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guitar_seq")
    long id;
    String brand;
    String model;
    int price;


    public Guitar(String brand, String model, int price) {

        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Guitar() {}

    public long getId() {
        return id;
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
