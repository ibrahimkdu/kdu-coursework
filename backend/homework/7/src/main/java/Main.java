import Config.appConfig;
import entitities.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.Vehicleservice;

import java.util.List;

public class Main {
    private static final Logger customLogger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(appConfig.class)) {
            Vehicleservice vehicleNo1 = context.getBean(Vehicleservice.class);
            List<Vehicle> vehicleListNo1 = vehicleNo1.getVehicles();

            customLogger.info("Vehicle Factory1 with default autowiring, Lucknow as city, and charges percentage as 10 ");
            customLogger.info("All Vehicles located in Factory1 in Lucknow are: ");
            vehicleListNo1.forEach(v -> customLogger.info(v.toString()));
            customLogger.info("Most Expensive Vehicle in Factory1 located in Lucknow is {}", vehicleNo1.mostExpensiveVehicle());
            customLogger.info("The least expensive vehicle in Factory1 located in Lucknow is {}", vehicleNo1.mostCheapVehicle());
            customLogger.info("Factory 2 created with location as Bangalore and charges percentage as 20 ");
            Vehicleservice vehicleNo2 = context.getBean(Vehicleservice.class);
            vehicleNo2.getLocation().setName("bangalore");
            vehicleNo2.getLocation().setCharges(20);
            vehicleNo2.generateVehicles();
            customLogger.info("Most expensive vehicle created in Factory2 in Bangalore {}", vehicleNo2.mostExpensiveVehicle());
            customLogger.info("Cheapest vehicle created in Factory2 in Bangalore {}", vehicleNo2.mostCheapVehicle());
        }
    }
}
