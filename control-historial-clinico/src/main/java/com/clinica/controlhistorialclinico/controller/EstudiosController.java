package com.clinica.controlhistorialclinico.controller;

import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.model.Estudios;
import com.clinica.controlhistorialclinico.service.EstudiosService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/estudios")
@Log4j2
public class EstudiosController {

    private EstudiosService service;

    @Autowired
    private void setService(EstudiosService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllEstudios() {
        try {
            log.info("Consulta de todos los estudios.");
            return ResponseEntity.ok().body(service.getAllEstudios());
        } catch (CHCError e) {
            log.warn("No se encontraron estudios.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEstudiosById(@PathVariable("id") Long id) {
        try {
            log.info("Consulta de todos los estudios por id.");
            return ResponseEntity.ok().body(service.getEstudiosByPacienteId(id));
        } catch (CHCError e) {
            log.warn("No se encontraron estudios.");
            log.error(e);
            return new ResponseEntity<>("No se encontraron datos.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createEstudio(@RequestBody @Validated Estudios exploracionFisica) {
        try {
            log.info("Estudio insertado.");
            Estudios response = service.createEstudios(exploracionFisica);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (HttpClientErrorException.NotFound | HttpClientErrorException.BadRequest e) {
            log.error("Paciente no encontrado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente no encontrado.");
        } catch (DataIntegrityViolationException e) {
            log.error("Datos inválidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos.");
        } catch (Exception e) {
            log.error("Error al crear el estudio: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al registrar el estudio.");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEstudio(@PathVariable("id") Long id) {
        try {
            log.info("Estudio con id %s eliminado".formatted(id));
            service.deleteEstudiosByPacienteId(id);
        }catch (Exception e) {
            log.error("Error al eliminar el estudio: ", e);
        }
    }
    
}
