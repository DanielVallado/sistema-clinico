package com.clinica.controlhistorialclinico.controller;

import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.model.ExploracionFisica;
import com.clinica.controlhistorialclinico.service.ExploracionFisicaService;
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

    @Autowired
    private ExploracionFisicaService service;

    @GetMapping
    public ResponseEntity<?> getAllExploracionesFisicas() {
        try {
            log.info("Consulta de todos las exploraciones fisicas.");
            return ResponseEntity.ok().body(service.getAllExploraciones());
        } catch (CHCError e) {
            log.warn("No se encontraron exploraciones fisicas.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExploracionesFisicasById(@PathVariable("id") Long id) {
        try {
            log.info("Consulta de todos las exploraciones fisicas.");
            return ResponseEntity.ok().body(service.getExploracionesByPacienteId(id));
        } catch (CHCError e) {
            log.warn("No se encontraron exploraciones fisicas.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
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
        } catch (CHCError e) {
            log.error("Paciente no registrado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente no registrado.");
        } catch (DataIntegrityViolationException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al crear el paciente: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al registrar la exploracion fisica.");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteExploracionFisica(@PathVariable("id") Long id) {
        try {
            log.info("Exploracion fisica con id %s eliminada".formatted(id));
            service.deleteExploracionByPacienteId(id);
        }catch (Exception e) {
            log.error("Error al eliminar la exploracion fisica: ", e);
        }
    }

}
