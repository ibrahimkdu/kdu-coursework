package com.kdu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.jpahw.repository")
@EntityScan("com.example.jpahw.model")
@SpringBootApplication
public class JpahandsonApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpahandsonApplication.class, args);
	}
}
