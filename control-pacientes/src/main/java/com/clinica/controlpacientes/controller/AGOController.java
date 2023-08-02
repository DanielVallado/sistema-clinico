package com.clinica.controlpacientes.controller;

import com.clinica.controlpacientes.error.CPException;
import com.clinica.controlpacientes.service.AGOService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/antecedentes-gineco-obstetricos")
@Log4j2
public class AGOController {

    @Autowired
    private AGOService service;

    @GetMapping
    public ResponseEntity<?> getAllAGO() {
        try {
            log.info("Consulta de todos los antecedentes gineco obstetricos.");
            return ResponseEntity.ok().body(service.getAllAGO());
        } catch (CPException e) {
            log.warn("No se encontraron antecedentes gineco obstetricos.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAGOById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(service.getAGOById(id));
        } catch (CPException e) {
            log.warn("No se encontraron antecedentes gineco obstetricos.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

}
