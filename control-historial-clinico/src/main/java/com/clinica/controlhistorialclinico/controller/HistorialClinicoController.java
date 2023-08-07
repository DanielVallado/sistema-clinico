package com.clinica.controlhistorialclinico.controller;

import com.clinica.controlhistorialclinico.error.CHCException;
import com.clinica.controlhistorialclinico.service.HistorialClinicoService;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
