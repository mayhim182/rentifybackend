package com.rental.rentify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.rental.rentify")
public class RentifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentifyApplication.class, args);
	}

}
