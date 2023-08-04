package com.clinica.controlcitas.controller;

import com.clinica.controlcitas.service.CitaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/citas")
@Log4j2
public class CitaController {

    private CitaService service;

    @Autowired
    private void setService(CitaService service) {
        this.service = service;
    }

}
