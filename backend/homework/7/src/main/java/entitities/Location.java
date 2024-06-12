package entitities;

public class Location {
    private String name;
    private double charges;

    public Location(String name, double charges) {
        this.name = name;
        this.charges = charges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }
}
