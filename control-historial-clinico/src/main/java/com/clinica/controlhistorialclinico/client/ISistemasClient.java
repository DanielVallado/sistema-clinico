package com.clinica.controlhistorialclinico.client;

import com.clinica.controlhistorialclinico.dto.client.SistemaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "control-sistemas", path = "/control-sistemas/sistemas")
public interface ISistemasClient {

    @GetMapping("/{sistema-id}")
    ResponseEntity<SistemaDTO> findSistemaById(@PathVariable("sistema-id") Long pacienteId);

}
