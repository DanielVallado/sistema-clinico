package com.clinica.controlhistorialclinico.controller;

import com.clinica.controlhistorialclinico.error.CHCException;
import com.clinica.controlhistorialclinico.model.Diagnostico;
import com.clinica.controlhistorialclinico.service.DiagnosticoService;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diagnostico")
@Log4j2
public class DiagnosticoController {

    private DiagnosticoService service;

    @Autowired
    private void setService(DiagnosticoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllDiagnosticos() {
        try {
            log.info("Consulta de todos los diagnosticos.");
            return ResponseEntity.ok().body(service.getAllDiagnosticos());
        } catch (FeignException e) {
            log.warn("Pacientes y/o estudios no encontrados.");
            return new ResponseEntity<>("Pacientes y/o estudios no encontrados.", HttpStatus.NOT_FOUND);
        } catch (CHCException e) {
            log.warn("No se encontraron diagnosticos.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{paciente-id}")
    public ResponseEntity<?> getDiagnosticosByPacienteId(@PathVariable("paciente-id") Long id) {
        try {
            log.info("Consulta de todos los diagnosticos por id.");
            return ResponseEntity.ok().body(service.getDiagnosticosByPacienteId(id));
        } catch (FeignException e) {
            log.warn("Pacientes y/o estudios no encontrados.");
            return new ResponseEntity<>("Pacientes y/o estudios no encontrados.", HttpStatus.NOT_FOUND);
        } catch (CHCException e) {
            log.warn("No se encontraron diagnosticos.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createDiagnostico(@RequestBody @Validated Diagnostico diagnostico) {
        try {
            log.info("Diagnostico insertado.");
            Diagnostico response = service.createDiagnostico(diagnostico);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (FeignException e) {
            log.warn("Pacientes y/o estudios no encontrados.");
            return new ResponseEntity<>("Pacientes y/o estudios no encontrados.", HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al registrar el diagnostico: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al registrar el diagnostico.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiagnostico(@PathVariable("id") Long id) {
        try {
            log.info("Diagnostico con id %s eliminado".formatted(id));
            service.deleteDiagnostico(id);
            return new ResponseEntity<>("Diagnostico con id %s eliminada.".formatted(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al eliminar el diagnostico: ", e);
            return new ResponseEntity<>("Diagnostico con id %s eliminada.".formatted(id), HttpStatus.OK);

        }
    }

    @DeleteMapping("/paciente/{paciente-id}")
    public ResponseEntity<?> deleteDiagnosticoByPacienteId(@PathVariable("paciente-id") Long pacienteId) {
        try {
            log.info("Diagnostico con pacienteId %s eliminado".formatted(pacienteId));
            service.deleteDiagnosticoByPacienteId(pacienteId);
            return new ResponseEntity<>("Diagnostico con pacienteId %s eliminada.".formatted(pacienteId), HttpStatus.OK);
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente con id %s.".formatted(pacienteId), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error al eliminar el diagnostico: ", e);
            return new ResponseEntity<>("Error al eliminar el diagnostico.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/sistema/{sistema-id}")
    public ResponseEntity<?> deleteDiagnosticoBySistemaId(@PathVariable("sistema-id") Long sistemaId) {
        try {
            log.info("Diagnostico con pacienteId %s eliminado".formatted(sistemaId));
            service.deleteDiagnosticoBySistemaId(sistemaId);
            return new ResponseEntity<>("Diagnostico con sistemaId %s eliminada.".formatted(sistemaId), HttpStatus.OK);
        } catch (FeignException e) {
            log.warn("No se encontro el sistema.");
            return new ResponseEntity<>("No se encontro el sistema con id %s.".formatted(sistemaId), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error al eliminar el diagnostico: ", e);
            return new ResponseEntity<>("Error al eliminar el diagnostico.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
