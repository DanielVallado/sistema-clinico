package com.clinica.controlhistorialclinico.controller;

import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.model.Diagnostico;
import com.clinica.controlhistorialclinico.service.DiagnosticoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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
        } catch (CHCError e) {
            log.warn("No se encontraron diagnosticos.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiagnosticosById(@PathVariable("id") Long id) {
        try {
            log.info("Consulta de todos los diagnosticos por id.");
            return ResponseEntity.ok().body(service.getDiagnosticosByPacienteId(id));
        } catch (CHCError e) {
            log.warn("No se encontraron diagnosticos.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createDiagnostico(@RequestBody @Validated Diagnostico exploracionFisica) {
        try {
            log.info("Diagnostico insertado.");
            Diagnostico response = service.createDiagnostico(exploracionFisica);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (HttpClientErrorException.NotFound | HttpClientErrorException.BadRequest e) {
            log.error("Paciente no encontrado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente no encontrado.");
        } catch (DataIntegrityViolationException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al registrar el diagnostico: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al registrar el diagnostico.");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteDiagnostico(@PathVariable("id") Long id) {
        try {
            log.info("Diagnostico con id %s eliminado".formatted(id));
            service.deleteDiagnosticoByPacienteId(id);
        }catch (Exception e) {
            log.error("Error al eliminar el diagnostico: ", e);
        }
    }
    
}
