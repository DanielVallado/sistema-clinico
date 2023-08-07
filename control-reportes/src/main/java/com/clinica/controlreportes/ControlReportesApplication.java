package com.clinica.controlreportes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ControlReportesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlReportesApplication.class, args);
	}

}
