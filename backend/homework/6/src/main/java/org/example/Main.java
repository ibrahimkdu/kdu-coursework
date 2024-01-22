package org.example;
import org.example.config.Appconfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.example.services.VehicleService;
public class Main {

    public static void main(String[] args) {


        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Appconfig.class)) {


            // Get the VehicleService bean from the context
            VehicleService vehicleService = context.getBean(VehicleService.class);

            // Print the most expensive vehicle
            vehicleService.printMostExpensiveVehicle();


    }
}}
