package entitities;

public class Vehicle {
    private Tyre tyre;
    private Speaker speaker;
    private double price;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle(Tyre tyre, Speaker speaker, double price) {
        this.tyre = tyre;
        this.speaker = speaker;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Vehicle{ " +"vehiclename "+name+
                " tyre= " + tyre.getBrand() +
                ", speaker= " + speaker.getBrand() +
                ", price= " + price +
                '}';
    }
}
