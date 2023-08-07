package com.clinica.controlreportes.client;

import com.clinica.controlreportes.dto.client.CitaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "control-citas", path = "/control-citas/citas")
public interface ICitasClient {

    @GetMapping
    List<CitaDTO> getAllCitas();

}
