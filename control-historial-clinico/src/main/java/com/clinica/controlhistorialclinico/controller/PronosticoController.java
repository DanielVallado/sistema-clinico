package com.clinica.controlhistorialclinico.controller;

import com.clinica.controlhistorialclinico.error.CHCException;
import com.clinica.controlhistorialclinico.model.Pronostico;
import com.clinica.controlhistorialclinico.service.PronosticoService;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pronostico")
@Log4j2
public class PronosticoController {

    private PronosticoService service;

    @Autowired
    private void setService(PronosticoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllPronosticos() {
        try {
            log.info("Consulta de todos los pronosticos.");
            return ResponseEntity.ok().body(service.getAllPronosticos());
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente.", HttpStatus.NOT_FOUND);
        } catch (CHCException e) {
            log.warn("No se encontraron pronosticos.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPronosticosById(@PathVariable("id") Long id) {
        try {
            log.info("Consulta de todos los pronosticos por id.");
            return ResponseEntity.ok().body(service.getPronosticosByPacienteId(id));
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente.", HttpStatus.NOT_FOUND);
        } catch (CHCException e) {
            log.warn("No se encontraron pronosticos.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createPronostico(@RequestBody @Validated Pronostico pronostico) {
        try {
            log.info("Pronostico insertado.");
            Pronostico response = service.createPronostico(pronostico);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente.", HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al registrar el pronostico: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al registrar el pronostico.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePronostico(@PathVariable("id") Long id) {
        try {
            log.info("Pronostico con id %s eliminado".formatted(id));
            service.deletePronostico(id);
            return new ResponseEntity<>("Pronostico con id %s eliminado.".formatted(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al eliminar el pronostico: ", e);
            return new ResponseEntity<>("Pronostico con id %s eliminado.".formatted(id), HttpStatus.OK);
        }
    }

    @DeleteMapping("/paciente/{paciente-id}")
    public ResponseEntity<?> deletePronosticoByPacienteId(@PathVariable("paciente-id") Long pacienteId) {
        try {
            log.info("Pronostico con pacienteId %s eliminado".formatted(pacienteId));
            service.deletePronosticoByPacienteId(pacienteId);
            return new ResponseEntity<>("Pronostico con pacienteId %s eliminado.".formatted(pacienteId), HttpStatus.OK);
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente con id %s.".formatted(pacienteId), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error al eliminar el pronostico: ", e);
            return new ResponseEntity<>("Error al eliminar el pronostico.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
