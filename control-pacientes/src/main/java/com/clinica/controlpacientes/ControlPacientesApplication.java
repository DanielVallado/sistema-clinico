package com.clinica.controlpacientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ControlPacientesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControlPacientesApplication.class, args);
    }

}
