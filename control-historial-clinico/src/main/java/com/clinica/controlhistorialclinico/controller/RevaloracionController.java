package com.clinica.controlhistorialclinico.controller;

import com.clinica.controlhistorialclinico.error.CHCException;
import com.clinica.controlhistorialclinico.model.Revaloracion;
import com.clinica.controlhistorialclinico.service.RevaloracionService;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/revaloracion")
@Log4j2
public class RevaloracionController {

    private RevaloracionService service;

    @Autowired
    private void setService(RevaloracionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllRevaloraciones() {
        try {
            log.info("Consulta de todos las revaloraciones.");
            return ResponseEntity.ok().body(service.getAllRevaloraciones());
        } catch (FeignException e) {
            log.warn("Pacientes y/o estudios no encontrados.");
            return new ResponseEntity<>("Pacientes y/o estudios no encontrados.", HttpStatus.NOT_FOUND);
        } catch (CHCException e) {
            log.warn("No se encontraron revaloraciones.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRevaloracionesById(@PathVariable("id") Long id) {
        try {
            log.info("Consulta de todos las revaloraciones por id.");
            return ResponseEntity.ok().body(service.getRevaloracionesByPacienteId(id));
        } catch (FeignException e) {
            log.warn("Pacientes y/o estudios no encontrados.");
            return new ResponseEntity<>("Pacientes y/o estudios no encontrados.", HttpStatus.NOT_FOUND);
        } catch (CHCException e) {
            log.warn("No se encontraron revaloraciones.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createRevaloracion(@RequestBody @Validated Revaloracion revaloracion) {
        try {
            log.info("Revaloracion insertado.");
            Revaloracion response = service.createRevaloracion(revaloracion);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (FeignException e) {
            log.warn("Pacientes y/o estudios no encontrados.");
            return new ResponseEntity<>("Pacientes y/o estudios no encontrados.", HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al registrar la revaloracion: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al registrar la revaloracion.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRevaloracion(@PathVariable("id") Long id) {
        try {
            log.info("Revaloracion con id %s eliminada".formatted(id));
            service.deleteRevaloracion(id);
            return new ResponseEntity<>("Revaloracion con id %s eliminada.".formatted(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al eliminar la revaloracion: ", e);
            return new ResponseEntity<>("Revaloracion con id %s eliminada.".formatted(id), HttpStatus.OK);
        }
    }

    @DeleteMapping("/paciente/{paciente-id}")
    public ResponseEntity<?> deleteRevaloracionByPacienteId(@PathVariable("paciente-id") Long pacienteId) {
        try {
            log.info("Revaloracion con pacienteId %s eliminado".formatted(pacienteId));
            service.deleteRevaloracionByPacienteId(pacienteId);
            return new ResponseEntity<>("Revaloracion con pacienteId %s eliminada.".formatted(pacienteId), HttpStatus.OK);
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente con id %s.".formatted(pacienteId), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error al eliminar el diagnostico: ", e);
            return new ResponseEntity<>("Error al eliminar la revaloracion.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/sistema/{sistema-id}")
    public ResponseEntity<?> deleteRevaloracionBySistemaId(@PathVariable("sistema-id") Long sistemaId) {
        try {
            log.info("Revaloracion con pacienteId %s eliminado".formatted(sistemaId));
            service.deleteRevaloracionBySistemaId(sistemaId);
            return new ResponseEntity<>("Revaloracion con sistemaId %s eliminada.".formatted(sistemaId), HttpStatus.OK);
        } catch (FeignException e) {
            log.warn("No se encontro el sistema.");
            return new ResponseEntity<>("No se encontro el sistema con id %s.".formatted(sistemaId), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error al eliminar el diagnostico: ", e);
            return new ResponseEntity<>("Error al eliminar la revaloracion.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
