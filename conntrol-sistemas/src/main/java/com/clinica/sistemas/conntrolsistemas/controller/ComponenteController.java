package com.clinica.sistemas.conntrolsistemas.controller;

import com.clinica.sistemas.conntrolsistemas.model.Componente;
import com.clinica.sistemas.conntrolsistemas.service.ComponenteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/componentes")
@Log4j2
public class ComponenteController {
    @Autowired
    private ComponenteService componenteService;

    @GetMapping
    public List<Componente> getAllcomponentes(){
        return componenteService.getAllcomponentes();
    }
    @PostMapping
    public Componente createComponentes(@RequestBody Componente componente){
        log.info("Componentes a guardar: " + componente.toString());
        return componenteService.createComponente(componente);
    }
    @PutMapping
    public Componente updateComponentes(@RequestBody Componente componente){
        log.info("Componentes a modificar: " + componente);
        return  componenteService.updateComponente(componente);
    }

    @DeleteMapping("/{id}")
    public void  deleteComponentes(@PathVariable(value = "id") Long id){
        componenteService.delateComponente(id);
    }
}
