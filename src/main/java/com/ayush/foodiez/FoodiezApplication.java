package com.ayush.foodiez;

import com.ayush.foodiez.model.Vendor;
import com.ayush.foodiez.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodiezApplication {


	public static void main(String[] args) {
		SpringApplication.run(FoodiezApplication.class, args);

	}


}
