package com.clinica.controlhistorialclinico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ControlHistorialClinicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControlHistorialClinicoApplication.class, args);
    }

}
