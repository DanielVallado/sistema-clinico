package com.clinica.sistemas.conntrolsistemas.service;

import com.clinica.sistemas.conntrolsistemas.model.Componente;
import com.clinica.sistemas.conntrolsistemas.repository.ComponenteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ComponenteService {
    @Autowired
    private ComponenteRepository componenteRepository;
    public Componente createComponente(Componente componente){
        log.info("Crea componente: " + componente.toString());
        return componenteRepository.save(componente);
    }
    public Componente updateComponente(Componente componente){
        log.info("Modifica Componente: " + componente);
        return  componenteRepository.save(componente);
    }
    public void delateComponente(Long id){
        componenteRepository.deleteById(id);
    }
    public List<Componente> getAllcomponentes() {
        return componenteRepository.findAll();
    }

}
