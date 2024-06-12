package services;

import entitities.Location;
import entitities.Speaker;
import entitities.Tyre;
import entitities.Vehicle;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Component
@Scope("prototype")
public class Vehicleservice {

    private Tyreservice tyreService;

    @Autowired
    private Speakerservice speakerService;

    @Autowired
    public void setTyreService(Tyreservice tyreService) {
        this.tyreService = tyreService;
    }

    private final Inventory inventory;
    private Location location;
    private final List<String> names = new ArrayList<>(Arrays.asList("Ford", "Mustang Gt", "LAMBORGHINI", "Ferrari"));

    @Autowired
    public Vehicleservice(Inventory inventory, @Qualifier("lucknow") Location location) {
        this.inventory = inventory;
        this.location = location;
    }

    private List<Vehicle> vehicles;
    private final Random random = new Random();

    /**
            * Initializes the service after construction, generating a list of vehicles.
     */
    @PostConstruct
    public void init() {
        vehicles = generateVehicles();
    }
    /**
     * Generates a list of vehicles with random characteristics.
     *
     * @return A list of randomly generated vehicles.
     */
    public List<Vehicle> generateVehicles() {
        List<Vehicle> vehicleList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tyre tyre = tyreService.getTyre();
            Speaker speaker = speakerService.getSpeaker();
            String nameOfCar = names.get(random.nextInt(names.size()));
            speaker.setPrice(speaker.getPrice() * location.getCharges());
            tyre.setPrice(speaker.getPrice() * location.getCharges());
            Vehicle obj = new Vehicle(tyre, speaker, random.nextInt() * 10000 + speaker.getPrice() + tyre.getPrice());
            obj.setName(nameOfCar);
            vehicleList.add(obj);
            inventory.addVehicle(obj);
        }
        return vehicleList;
    }

    public Vehicle mostExpensiveVehicle() {
        return vehicles.stream().max(Comparator.comparingDouble(Vehicle::getPrice)).orElse(null);
    }

    public Vehicle mostCheapVehicle() {
        return vehicles.stream().min(Comparator.comparingDouble(Vehicle::getPrice)).orElse(null);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Vehicle> getInventory() {
        return inventory.getVehicles();
    }

    public Location getLocation() {
        return location;
    }
}
