package config;

import entitities.Location;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "services")
public class Appconfig {
    @Bean("lucknow")
    public Location getLocation1(){
        return new Location("Lucknow",10);
    }
    @Bean("bengaluru")
    public Location getLocation2(){
        return new Location("Bengaluru",20);
    }
}
