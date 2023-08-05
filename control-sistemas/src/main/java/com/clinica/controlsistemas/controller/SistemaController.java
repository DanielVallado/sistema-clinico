package com.clinica.controlsistemas.controller;


import com.clinica.controlsistemas.error.CSException;
import com.clinica.controlsistemas.model.Sistema;
import com.clinica.controlsistemas.service.SistemaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sistemas")
@Log4j2

public class SistemaController {

    private SistemaService service;

    @Autowired
    private void setService(SistemaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllSistemas() {
        try {
            log.info("Consulta de todos los sistemas.");
            return ResponseEntity.ok().body(service.getAllSistemas());
        } catch (CSException e) {
            log.warn("No se encontraron sistemas.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSistemaById(@PathVariable("id") Long id) {
        try {
            log.info("Consulta de todos los sistemas.");
            return ResponseEntity.ok().body(service.getSistemaById(id));
        } catch (CSException e) {
            log.warn("No se encontraron sistemas.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createSistema(@RequestBody @Validated Sistema sistema) {
        try {
            log.info("Sistema %s insertado".formatted(sistema.getNombre()));
            Sistema createdSistema = service.createSistema(sistema);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSistema);
        } catch (DataIntegrityViolationException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al crear el sistema: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al crear el sistema.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSistema(@PathVariable("id") Long id) {
        try {
            log.info("Sistema con id %s eliminado".formatted(id));
            service.deleteSistema(id);
            return new ResponseEntity<>("Sistema con id %s eliminado.".formatted(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al eliminar el sistema: ", e);
            return new ResponseEntity<>("Error al eliminar el sistema.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
