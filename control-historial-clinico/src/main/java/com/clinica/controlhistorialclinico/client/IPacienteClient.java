package com.clinica.controlhistorialclinico.client;

import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "control-pacientes", path = "/control-pacientes/pacientes")
public interface IPacienteClient {

    @GetMapping("/{paciente-id}")
    ResponseEntity<PacienteDTO> findByPacienteId(@PathVariable("paciente-id") Long pacienteId);

}
