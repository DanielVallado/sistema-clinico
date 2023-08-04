package com.clinica.sistemas.conntrolsistemas.service;

import com.clinica.sistemas.conntrolsistemas.model.Sistema;
import com.clinica.sistemas.conntrolsistemas.repository.SistemaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class SistemaService {
    @Autowired
    private SistemaRepository sistemaRepository;

    public Sistema createSistema(Sistema sistema){
        log.info("Crea sistema: " + sistema.toString());
        return sistemaRepository.save(sistema);
    }
    public Sistema updateSistema(Sistema sistema){
        log.info("Modifica Sistema: " + sistema);
        return  sistemaRepository.save(sistema);
    }
    public void delateSistema(Long id){
        sistemaRepository.deleteById(id);
    }
    public List<Sistema> getAllsistemas() {
        return sistemaRepository.findAll();
    }

}
