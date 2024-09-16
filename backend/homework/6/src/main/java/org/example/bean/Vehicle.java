package org.example.bean;

public class Vehicle {
    String name;

    double price;

    private Tyre tyre1;

    private Speaker speaker1;
    public Vehicle(Tyre tyre1,Speaker speaker1)
    {
        this.tyre1=tyre1;
        this.speaker1=speaker1;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price+ tyre1.getPrice()+ speaker1.getPrice();
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", tyre1=" + tyre1.getName() +
                ", speaker1=" + speaker1.getName() +
                '}';
    }
}
