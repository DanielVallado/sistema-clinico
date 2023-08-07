package com.clinica.controlreportes.controller;

import com.clinica.controlreportes.service.ReporteService;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/reportes")
@Log4j2
public class ReporteController {

    private ReporteService service;

    @Autowired
    private void setService(ReporteService service) {
        this.service = service;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/historial-clinico/{paciente-id}")
    public ResponseEntity<?> getHistorialClinicoByPacienteId(@PathVariable("paciente-id") Long id) {
        try {
            log.info("Consulta de historial clínico por id.");
            return ResponseEntity.ok().body(service.getHistorialClinicoByPacienteId(id));
        } catch (FeignException e) {
            log.warn("Pacientes y/o estudios no encontrados.");
            return new ResponseEntity<>("Pacientes y/o estudios no encontrados.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/citas")
    public ResponseEntity<?> getCitas(@RequestParam(value = "fecha-inicial") Date fechaInicial, @RequestParam("fecha-final") Date fechaFinal) {
        try {
            log.info("Consulta de citas.");
            return ResponseEntity.ok().body(service.getCitas(fechaInicial, fechaFinal));
        } catch (FeignException e) {
            log.warn("Pacientes y/o estudios no encontrados.");
            return new ResponseEntity<>("Pacientes y/o estudios no encontrados.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/citas-canceladas")
    public ResponseEntity<?> getCitasCanceladas(@RequestParam(value = "fecha-inicial") Date fechaInicial, @RequestParam("fecha-final") Date fechaFinal) {
        try {
            log.info("Consulta de citas canceladas-");
            return ResponseEntity.ok().body(service.getCitasCanceladas(fechaInicial, fechaFinal));
        } catch (FeignException e) {
            log.warn("Pacientes y/o estudios no encontrados.");
            return new ResponseEntity<>("Pacientes y/o estudios no encontrados.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

    @GetMapping("/pacientes-subsecuentes")
    public ResponseEntity<?> getPacientesSubsecuentes(@RequestParam(value = "fecha-inicial") Date fechaInicial, @RequestParam("fecha-final") Date fechaFinal) {
        try {
            log.info("Consulta de pacientes subsecuentes.");
            return ResponseEntity.ok().body(service.getPacientesSubsecuentes(fechaInicial, fechaFinal));
        } catch (FeignException e) {
            log.warn("Pacientes no encontrados.");
            return new ResponseEntity<>("Pacientes no encontrados.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al consultar los datos.");
        }
    }

}
