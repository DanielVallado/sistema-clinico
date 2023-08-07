package com.clinica.controlpacientes.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "control-citas", path = "/control-citas/citas")
public interface ICitasClient {

    @DeleteMapping
    ResponseEntity<String> deleteCitaByPacienteId(@PathVariable("paciente-id") Long pacienteId);

}
