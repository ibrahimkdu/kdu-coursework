package entitities;
import constants.Constants;

public class Speaker {
    private Constants.speakerBrand brand;
    private double price;

    public Speaker(Constants.speakerBrand brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public Constants.speakerBrand getBrand() {
        return brand;
    }

    public void setBrand(Constants.speakerBrand brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
