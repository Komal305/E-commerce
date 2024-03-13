package com.cg.northwind;
/*
 * @authors
 * 
 * M V Shashank Yadav
 * Deepak B
 * B Tharun
 * Satya Ventaka Sairam Bogireddy
 * Abdul Hameed Shahil
 * 
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "localhost:4200/")
public class NorthWindApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorthWindApplication.class, args);
	}

}
