package com.clinica.controlcitas.controller;

import com.clinica.controlcitas.dto.CitaDTO;
import com.clinica.controlcitas.error.CCException;
import com.clinica.controlcitas.model.Cita;
import com.clinica.controlcitas.service.CitaService;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citas")
@Log4j2
public class CitaController {

    private CitaService service;

    @Autowired
    private void setService(CitaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllCitas() {
        try {
            log.info("Consulta de todos las citas.");
            return ResponseEntity.ok().body(service.getAllCitas());
        } catch (CCException e) {
            log.warn("No se encontraron citas.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllCitasById(@PathVariable("id") Long id) {
        try {
            log.info("Consulta de todos las citas por pacienteId.");
            return ResponseEntity.ok().body(service.getCitasByPacienteId(id));
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            log.error(e.getMessage());
            log.error(e);
            return new ResponseEntity<>("No se encontro al paciente.", HttpStatus.NOT_FOUND);
        } catch (CCException e) {
            log.warn("No se encontraron citas.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createCita(@RequestBody @Validated Cita cita) {
        try {
            log.info("Cita insertada.");
            CitaDTO response = service.createCita(cita);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente.", HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException | HttpMessageNotReadableException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al registrar la cita: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al registrar la cita.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCita(@RequestBody @Validated Cita cita, @PathVariable("id") Long id) {
        try {
            log.info("Cita actualizada.");
            CitaDTO response = service.updateCita(id, cita);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente.", HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException | HttpMessageNotReadableException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al registrar la cita: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al actualizar la cita.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCita(@PathVariable("id") Long id) {
        try {
            log.info("Cita con id %s eliminada".formatted(id));
            service.deleteCita(id);
            return new ResponseEntity<>("Cita con id %s eliminada.".formatted(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al eliminar la cita: ", e);
            return new ResponseEntity<>("Error al eliminar la cita.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/paciente/{paciente-id}")
    public ResponseEntity<?> deleteCitaByPacienteId(@PathVariable("paciente-id") Long pacienteId) {
        try {
            log.info("Cita con pacienteId %s eliminado".formatted(pacienteId));
            service.deleteCitaByPacienteId(pacienteId);
            return new ResponseEntity<>("Cita con pacienteId %s eliminada.".formatted(pacienteId), HttpStatus.OK);
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente con id %s.".formatted(pacienteId), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error al eliminar la cita: ", e);
            return new ResponseEntity<>("Error al eliminar la cita.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/confirmar-cita")
    public ResponseEntity<?> confirmarCita(@RequestParam("id") Long id) {
        try {
            CitaDTO cita = service.confirmarCita(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(cita);
        } catch (CCException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro la cita con ID %s.".formatted(id), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error al eliminar la cita: ", e);
            return new ResponseEntity<>("Error al eliminar la cita.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cancelar-cita")
    public ResponseEntity<?> cancelarCita(@RequestParam("id") Long id) {
        try {
            CitaDTO cita = service.cancelarCita(id);
            return ResponseEntity.status(HttpStatus.OK).body(cita);
        } catch (CCException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro la cita con ID %s.".formatted(id), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error al eliminar la cita: ", e);
            return new ResponseEntity<>("Error al eliminar la cita.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
