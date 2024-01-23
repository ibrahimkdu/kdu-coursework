package services;

import entitities.Vehicle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Inventory {
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    /**
     * Adds a vehicle to the inventory.
     *
     * @param vehicle The vehicle to be added to the inventory.
     */
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
    /**
     * Retrieves the list of vehicles in the inventory.
     *
     * @return The list of vehicles in the inventory.
     */
    public List<Vehicle> getVehicles() {
        return vehicles;

    }
}
