package com.clinica.controlhistorialclinico.controller;

import com.clinica.controlhistorialclinico.error.CHCException;
import com.clinica.controlhistorialclinico.model.ExploracionFisica;
import com.clinica.controlhistorialclinico.service.ExploracionFisicaService;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exploracion-fisica")
@Log4j2
public class ExploracionFisicaController {

    private ExploracionFisicaService service;

    @Autowired
    private void setService(ExploracionFisicaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllExploracionesFisicas() {
        try {
            log.info("Consulta de todos las exploraciones fisicas.");
            return ResponseEntity.ok().body(service.getAllExploraciones());
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente.", HttpStatus.NOT_FOUND);
        } catch (CHCException e) {
            log.warn("No se encontraron exploraciones fisicas.");
            log.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllExploracionesFisicasById(@PathVariable("id") Long id) {
        try {
            log.info("Consulta de todos las exploraciones fisicas por id.");
            return ResponseEntity.ok().body(service.getExploracionesByPacienteId(id));
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente.", HttpStatus.NOT_FOUND);
        } catch (CHCException e) {
            log.warn("No se encontraron exploraciones fisicas.");
            log.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createExploracionFisica(@RequestBody @Validated ExploracionFisica exploracionFisica) {
        try {
            log.info("Exploracion fisica insertada.");
            ExploracionFisica response = service.createExploracion(exploracionFisica);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente.", HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al registrar exploracion fisica: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al registrar la exploracion fisica.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExploracionFisica(@PathVariable("id") Long id) {
        try {
            log.info("Exploracion fisica con id %s eliminada".formatted(id));
            service.deleteExploracion(id);
            return new ResponseEntity<>("Exploracion fisica con id %s eliminada.".formatted(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al eliminar la exploracion fisica: ", e);
            return new ResponseEntity<>("Error al eliminar la exploracion fisica.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/paciente/{paciente-id}")
    public ResponseEntity<?> deleteExploracionFisicaByPacienteId(@PathVariable("paciente-id") Long pacienteId) {
        try {
            log.info("Exploracion fisica con pacienteId %s eliminado".formatted(pacienteId));
            service.deleteExploracionByPacienteId(pacienteId);
            return new ResponseEntity<>("Exploracion fisica con pacienteId %s eliminada.".formatted(pacienteId), HttpStatus.OK);
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente con id %s.".formatted(pacienteId), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error al eliminar la exploracion fisica: ", e);
            return new ResponseEntity<>("Error al eliminar la exploracion fisica.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
