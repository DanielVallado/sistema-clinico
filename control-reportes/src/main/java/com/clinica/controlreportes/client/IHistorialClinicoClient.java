package com.clinica.controlreportes.client;

import com.clinica.controlreportes.dto.client.HistorialClinicoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "control-historial-clinico", path = "/control-historial-clinico/historial-clinico")
public interface IHistorialClinicoClient {

    @GetMapping
    List<HistorialClinicoDTO> getAllHistorialesClinicos();

    @GetMapping("/{paciente-id}")
    ResponseEntity<HistorialClinicoDTO> getHistorialClinicoByPacienteId(@PathVariable("paciente-id") Long pacienteId);

}
