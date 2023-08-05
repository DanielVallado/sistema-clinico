package com.clinica.controlhistorialclinico.controller;

import com.clinica.controlhistorialclinico.error.CHCException;
import com.clinica.controlhistorialclinico.model.Estudios;
import com.clinica.controlhistorialclinico.service.EstudiosService;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudios")
@Log4j2
public class EstudiosController {

    private EstudiosService service;

    @Autowired
    private void setService(EstudiosService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllEstudios() {
        try {
            log.info("Consulta de todos los estudios.");
            return ResponseEntity.ok().body(service.getAllEstudios());
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente.", HttpStatus.NOT_FOUND);
        } catch (CHCException e) {
            log.warn("No se encontraron estudios.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEstudiosById(@PathVariable("id") Long id) {
        try {
            log.info("Consulta de todos los estudios por id.");
            return ResponseEntity.ok().body(service.getEstudiosByPacienteId(id));
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente.", HttpStatus.NOT_FOUND);
        } catch (CHCException e) {
            log.warn("No se encontraron estudios.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createEstudio(@RequestBody @Validated Estudios estudios) {
        try {
            log.info("Estudio insertado.");
            Estudios response = service.createEstudios(estudios);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente.", HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al crear los estudios: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al registrar los estudios.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstudio(@PathVariable("id") Long id) {
        try {
            log.info("Estudios con id %s eliminados.".formatted(id));
            service.deleteEstudios(id);
            return new ResponseEntity<>("Estudios con id %s eliminados.".formatted(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al eliminar los estudios: ", e);
            return new ResponseEntity<>("Estudios con id %s eliminados.".formatted(id), HttpStatus.OK);
        }
    }

    @DeleteMapping("/paciente/{paciente-id}")
    public ResponseEntity<?> deleteEstudioByPacienteId(@PathVariable("paciente-id") Long pacienteId) {
        try {
            log.info("Estudios con pacienteId %s eliminados.".formatted(pacienteId));
            service.deleteEstudiosByPacienteId(pacienteId);
            return new ResponseEntity<>("Estudios con pacienteId %s eliminados.".formatted(pacienteId), HttpStatus.OK);
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente con id %s.".formatted(pacienteId), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error al eliminar los estudios: ", e);
            return new ResponseEntity<>("Error al eliminar los estudios.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
