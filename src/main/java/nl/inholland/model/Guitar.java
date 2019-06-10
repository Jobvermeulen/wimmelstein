package nl.inholland.model;

import lombok.Data;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.*;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"brand", "model"})})
public class Guitar {


    @Id
    @SequenceGenerator(name="guitar_seq", initialValue = 1000001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guitar_seq")
    long id;
    String brand;
    String model;
    String image;
    int price;
    String description;


//    public Guitar(String brand, String model, String description, int price) {
//
//        this.brand = brand;
//        this.model = model;
//        this.price = price;
//        this.description = description;
//    }


    public Guitar(String brand, String model, String image, int price, String description) {
        this.brand = brand;
        this.model = model;
        this.image = image;
        this.price = price;
        this.description = description;
    }

    public Guitar() {}

    public void setId(long id) {
        throw new NotImplementedException();
    }
}
