package com.clinica.controlcitas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ControlCitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlCitasApplication.class, args);
	}

}
