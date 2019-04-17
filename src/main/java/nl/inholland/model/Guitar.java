package nl.inholland.model;

public class Guitar {

    String id;
    String brand;
    String model;

    public Guitar(String id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }

    public Guitar() {}

    public String getId() {
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

    @Override
    public String toString() {
        return "Guitar{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
