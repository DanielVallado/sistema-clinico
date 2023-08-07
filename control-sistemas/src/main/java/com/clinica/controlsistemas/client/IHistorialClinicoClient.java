package com.clinica.controlsistemas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "control-historial-clinico", path = "/control-historial-clinico/historial-clinico/sistema")
public interface IHistorialClinicoClient {

    @DeleteMapping
    ResponseEntity<?> deleteHistorialClinicoBySistemaId(@PathVariable("sistema-id") Long sistemaId);

}
