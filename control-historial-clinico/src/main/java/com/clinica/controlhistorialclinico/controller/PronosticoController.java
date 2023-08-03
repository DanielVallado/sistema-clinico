package com.clinica.controlhistorialclinico.controller;

import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.model.Pronostico;
import com.clinica.controlhistorialclinico.service.PronosticoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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
        } catch (CHCError e) {
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
        } catch (CHCError e) {
            log.warn("No se encontraron pronosticos.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createPronostico(@RequestBody @Validated Pronostico exploracionFisica) {
        try {
            log.info("Pronostico insertado.");
            Pronostico response = service.createPronostico(exploracionFisica);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (HttpClientErrorException.NotFound | HttpClientErrorException.BadRequest e) {
            log.error("Paciente no encontrado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente no encontrado.");
        } catch (DataIntegrityViolationException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al registrar el pronostico: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al registrar el pronostico.");
        }
    }

    @DeleteMapping("/{id}")
    public void deletePronostico(@PathVariable("id") Long id) {
        try {
            log.info("Pronostico con id %s eliminado".formatted(id));
            service.deletePronosticoByPacienteId(id);
        }catch (Exception e) {
            log.error("Error al eliminar el pronostico: ", e);
        }
    }
    
}
