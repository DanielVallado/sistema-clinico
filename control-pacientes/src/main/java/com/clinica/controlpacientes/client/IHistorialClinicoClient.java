package com.clinica.controlpacientes.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "control-historial-clinico", path = "/control-historial-clinico/historial-clinico/paciente")
public interface IHistorialClinicoClient {

    @DeleteMapping
    ResponseEntity<String> deleteHistorialClinicoByPacienteId(@PathVariable("paciente-id") Long pacienteId);

}
