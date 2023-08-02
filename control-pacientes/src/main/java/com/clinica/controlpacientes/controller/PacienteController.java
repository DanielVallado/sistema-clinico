package com.clinica.controlpacientes.controller;

import com.clinica.controlpacientes.error.CPException;
import com.clinica.controlpacientes.model.Paciente;
import com.clinica.controlpacientes.service.PacienteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
@Log4j2
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<?> getAllPacientes() {
        try {
            log.info("Consulta de todos los pacientes.");
            return ResponseEntity.ok().body(service.getAllPacientesDto());
        } catch (CPException e) {
            log.warn("No se encontraron pacientes.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPacienteById(@PathVariable("id") Long id) {
        try {
            log.info("Consulta de todos los pacientes.");
            return ResponseEntity.ok().body(service.getPacienteDtoById(id));
        } catch (CPException e) {
            log.warn("No se encontraron pacientes.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createPaciente(@RequestBody @Validated Paciente paciente) {
        try {
            log.info("Paciente %s insertado".formatted(paciente.getNombre()));
            Paciente createdPaciente = service.createPaciente(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPaciente);
        } catch (DataIntegrityViolationException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al crear el paciente: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al crear el paciente.");
        }
    }

    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable("id") Long id) {
        try {
            log.info("Paciente con id %s eliminado".formatted(id));
            service.deletePaciente(id);
        }catch (Exception e) {
            log.error("Error al eliminar el paciente: ", e);
        }
    }

}
