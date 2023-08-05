package com.clinica.controlsistemas.controller;

import com.clinica.controlsistemas.error.CSException;
import com.clinica.controlsistemas.model.Aparato;
import com.clinica.controlsistemas.service.AparatoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/aparatos")
@Log4j2
public class AparatoController {

    private AparatoService service;

    @Autowired
    private void setService(AparatoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllAparatos() {
        try {
            log.info("Consulta de todos los aparatos.");
            return ResponseEntity.ok().body(service.getAllAparatos());
        } catch (CSException e) {
            log.warn("No se encontraron aparatos.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/sistema/{sistema-id}")
    public ResponseEntity<?> getAllAparatosBySistemaId(@PathVariable("sistema-id") Long sistemaId) {
        try {
            log.info("Consulta de todos los aparatos.");
            return ResponseEntity.ok().body(service.getAllAparatosBySistemaId(sistemaId));
        } catch (CSException e) {
            log.warn("No se encontraron aparatos.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAparatoById(@PathVariable("id") Long id) {
        try {
            log.info("Consulta de todos los aparatos.");
            return ResponseEntity.ok().body(service.getAparatoById(id));
        } catch (CSException e) {
            log.warn("No se encontraron aparatos.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createAparato(@RequestBody @Validated Aparato aparato, @RequestParam("sistema-id") Long sistemaId) {
        try {
            log.info("Aparato %s insertado".formatted(aparato.getNombre()));
            Aparato createdAparato = service.createAparato(aparato, sistemaId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAparato);
        } catch (CSException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontro el sistema con ID %s.".formatted(sistemaId));
        } catch (DataIntegrityViolationException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al crear el aparato: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al crear el aparato.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAparato(@PathVariable("id") Long id) {
        try {
            log.info("Aparato con id %s eliminado".formatted(id));
            service.deleteAparato(id);
            return new ResponseEntity<>("Aparato con id %s eliminado.".formatted(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al eliminar el aparato: ", e);
            return new ResponseEntity<>("Error al eliminar el aparato.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/sistema/{sistema-id}")
    public ResponseEntity<?> deleteAparatoBySistemaId(@PathVariable("sistema-id") Long sistemaId) {
        try {
            log.info("Aparato con sistemaId %s eliminado".formatted(sistemaId));
            service.deleteAparatoBySistemaId(sistemaId);
            return new ResponseEntity<>("Aparato con sistemaId %s eliminado.".formatted(sistemaId), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al eliminar el aparato: ", e);
            return new ResponseEntity<>("Error al eliminar el aparato.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
