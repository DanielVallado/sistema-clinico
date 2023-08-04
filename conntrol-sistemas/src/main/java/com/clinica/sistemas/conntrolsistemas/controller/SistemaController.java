package com.clinica.sistemas.conntrolsistemas.controller;

import com.clinica.sistemas.conntrolsistemas.model.Sistema;
import com.clinica.sistemas.conntrolsistemas.service.SistemaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sistemas")
@Log4j2

public class SistemaController {
    @Autowired
    private SistemaService sistemaService;

    @GetMapping
    public List<Sistema> getAllSistemas(){
        return sistemaService.getAllsistemas();
    }
    @PostMapping
    public Sistema createSistema(@RequestBody Sistema sistema){
        log.info("Sistema a guardar: " + sistema.toString());
        return sistemaService.createSistema(sistema);
    }
    @PutMapping
    public Sistema updateSistema(@RequestBody Sistema sistema){
        log.info("Sistema a modificar: " + sistema);
        return  sistemaService.updateSistema(sistema);
    }

    @DeleteMapping("/{id}")
    public void  deleteSistema(@PathVariable(value = "id") Long id){
        sistemaService.delateSistema(id);
    }
}
