package com.caching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Assignment2springbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Assignment2springbootApplication.class, args);
    }

}
