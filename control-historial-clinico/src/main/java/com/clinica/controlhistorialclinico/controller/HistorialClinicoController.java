package com.clinica.controlhistorialclinico.controller;

import com.clinica.controlhistorialclinico.error.CHCException;
import com.clinica.controlhistorialclinico.service.HistorialClinicoService;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historial-clinico")
@Log4j2
public class HistorialClinicoController {

    private HistorialClinicoService service;

    @Autowired
    private void setService(HistorialClinicoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllHistorialesClinicos() {
        try {
            log.info("Consulta de todos los diagnosticos por id.");
            return ResponseEntity.ok().body(service.getAllHistorialesClinicos());
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
    public ResponseEntity<?> getHistorialClinicoByPacienteId(@PathVariable("paciente-id") Long id) {
        try {
            log.info("Consulta de historial clinico por id.");
            return ResponseEntity.ok().body(service.getHistorialClinicoByPacienteId(id));
        } catch (FeignException e) {
            log.warn("Pacientes y/o estudios no encontrados.");
            return new ResponseEntity<>("Pacientes y/o estudios no encontrados.", HttpStatus.NOT_FOUND);
        } catch (CHCException e) {
            log.warn("No se encontro historial clinico.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @DeleteMapping("/paciente/{paciente-id}")
    public ResponseEntity<?> deleteHistorialClinicoByPacienteId(@PathVariable("paciente-id") Long pacienteId) {
        try {
            log.info("Historial clinico del paciente pacienteId %s eliminado".formatted(pacienteId));
            service.deleteHistorialClinicoByPacienteId(pacienteId);
            return new ResponseEntity<>("Historial clinico con pacienteId %s eliminada.".formatted(pacienteId), HttpStatus.OK);
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente con id %s.".formatted(pacienteId), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error al eliminar el historial clinico: ", e);
            return new ResponseEntity<>("Error al eliminar el historial clinico.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/sistema/{sistema-id}")
    public ResponseEntity<?> deleteHistorialClinicoBySistemaId(@PathVariable("sistema-id") Long sistemaId) {
        try {
            log.info("Historial clinico del paciente sistemaId %s eliminado".formatted(sistemaId));
            service.deleteHistorialClinicoBySistemaId(sistemaId);
            return new ResponseEntity<>("Historial clinico con sistemaId %s eliminada.".formatted(sistemaId), HttpStatus.OK);
        } catch (FeignException e) {
            log.warn("No se encontro al paciente.");
            return new ResponseEntity<>("No se encontro al paciente con id %s.".formatted(sistemaId), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error al eliminar el historial clinico: ", e);
            return new ResponseEntity<>("Error al eliminar el historial clinico.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
