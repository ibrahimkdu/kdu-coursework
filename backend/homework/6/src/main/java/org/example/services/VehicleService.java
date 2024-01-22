package org.example.services;

import jakarta.annotation.PostConstruct;
import org.example.bean.Speaker;
import org.example.bean.Tyre;
import org.example.bean.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class VehicleService {
    public static final Logger Logger = LoggerFactory.getLogger(VehicleService.class);
    private final TyreService tyreService;
    private final SpeakerService speakerService;

    private final List<Vehicle> vehicles;

    private final List<String> names = new ArrayList<>(Arrays.asList("Ford", "Mustang Gt", "LAMBORGHINI", "Ferrari"));

    private final Random random = new Random();

    @Autowired
    public VehicleService(TyreService tyreService, SpeakerService speakerService) {
        this.tyreService = tyreService;
        this.speakerService = speakerService;
        this.vehicles = new ArrayList<>();
    }

    @PostConstruct
    public List<Vehicle> generateVehicles() {
        for (int i = 0; i < 5; i++) {
            Tyre tyre = tyreService.getTyre();
            Speaker speaker = speakerService.getSpeaker();
            String nameOfCar = names.get(random.nextInt(names.size()));
            Vehicle obj = new Vehicle(tyre, speaker);
            obj.setName(nameOfCar);
            obj.setPrice(1000);
            vehicles.add(obj);
        }
        return vehicles;
    }

    public void printMostExpensiveVehicle() {
        Vehicle mostExpensive = vehicles.stream().max(Comparator.comparingDouble(Vehicle::getPrice)).orElse(null);
        if (mostExpensive != null) {
            Logger.info("Most expensive vehicle is {}",mostExpensive);
        }
    }
}
