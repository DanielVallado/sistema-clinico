package com.clinica.controlpacientes.controller;

import com.clinica.controlpacientes.error.CPException;
import com.clinica.controlpacientes.service.PAService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/padecimiento-actual")
@Log4j2
public class PAController {

    @Autowired
    private PAService service;

    @GetMapping
    public ResponseEntity<?> getAllPA() {
        try {
            log.info("Consulta de todos los padecimientos actuales.");
            return ResponseEntity.ok().body(service.getAllPA());
        } catch (CPException e) {
            log.warn("No se encontraron padecimientos actuales.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPAById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(service.getPAById(id));
        } catch (CPException e) {
            log.warn("No se encontraron padecimientos actuales.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

}
