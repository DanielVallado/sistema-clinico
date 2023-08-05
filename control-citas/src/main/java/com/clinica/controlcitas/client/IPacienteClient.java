package com.clinica.controlcitas.client;

import com.clinica.controlcitas.dto.client.PacienteDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "control-pacientes", path = "/control-pacientes/pacientes")
public interface IPacienteClient {

    @GetMapping("/{paciente-id}")
    ResponseEntity<PacienteDTO> findPacienteById(@PathVariable("paciente-id") Long pacienteId);

}
