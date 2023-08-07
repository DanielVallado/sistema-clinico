package com.clinica.controlsistemas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ControlSistemasApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControlSistemasApplication.class, args);
    }

}
