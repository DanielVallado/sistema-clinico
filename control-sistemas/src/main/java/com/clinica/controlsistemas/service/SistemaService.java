package com.clinica.controlsistemas.service;

import com.clinica.controlsistemas.dto.SistemaDTO;
import com.clinica.controlsistemas.error.CSException;
import com.clinica.controlsistemas.mapper.SistemaMapper;
import com.clinica.controlsistemas.model.Sistema;
import com.clinica.controlsistemas.repository.SistemaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class SistemaService {

    private SistemaRepository repository;

    @Autowired
    private void setRepository(SistemaRepository repository) {
        this.repository = repository;
    }

    public List<SistemaDTO> getAllSistemas() throws Exception {
        List<Sistema> sistemas = repository.findAll();
        if (sistemas.isEmpty()) {
            throw new CSException("No se encontraron datos.");
        }

        return sistemas.stream().map(SistemaMapper::mapToDTO).collect(Collectors.toList());
    }

    public SistemaDTO getSistemaById(Long id) throws Exception {
        Optional<Sistema> sistema = repository.findById(id);
        if (sistema.isEmpty()) {
            throw new CSException("No se encontraron datos.");
        }
        return SistemaMapper.mapToDTO(sistema.get());
    }

    public Sistema createSistema(Sistema sistema) {
        return repository.save(sistema);
    }

    public void deleteSistema(Long id) {
        repository.deleteById(id);
    }

}
