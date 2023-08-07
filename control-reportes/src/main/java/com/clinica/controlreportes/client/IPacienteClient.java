package com.clinica.controlreportes.client;

import com.clinica.controlreportes.dto.client.PacienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "control-pacientes", path = "/control-pacientes/pacientes")
public interface IPacienteClient {

    @GetMapping
    List<PacienteDTO> findAllPacientes();

    @GetMapping("/{paciente-id}")
    ResponseEntity<PacienteDTO> findPacienteById(@PathVariable("paciente-id") Long pacienteId);

}
