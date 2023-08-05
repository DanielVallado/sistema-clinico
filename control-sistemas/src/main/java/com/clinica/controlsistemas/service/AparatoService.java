package com.clinica.controlsistemas.service;

import com.clinica.controlsistemas.dto.AparatoDTO;
import com.clinica.controlsistemas.dto.SistemaDTO;
import com.clinica.controlsistemas.error.CSException;
import com.clinica.controlsistemas.mapper.AparatoMapper;
import com.clinica.controlsistemas.model.Aparato;
import com.clinica.controlsistemas.model.Sistema;
import com.clinica.controlsistemas.repository.AparatoRepository;
import com.clinica.controlsistemas.repository.SistemaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AparatoService {

    private AparatoRepository repository;
    private SistemaRepository sistemaRepository;
    private SistemaService sistemaService;

    @Autowired
    private void setRepository(AparatoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setSistemaRepository(SistemaRepository sistemaRepository) {
        this.sistemaRepository = sistemaRepository;
    }

    @Autowired
    private void setSistemaService(SistemaService sistemaService) {
        this.sistemaService = sistemaService;
    }

    public List<AparatoDTO> getAllAparatos() throws Exception {
        List<Aparato> listAparatos = repository.findAll();
        return mapAparatosToDTO(listAparatos);
    }

    public List<AparatoDTO> getAllAparatosBySistemaId(Long sistemaId) throws Exception {
        List<Aparato> listAparatos = repository.findAllBySistemaId(sistemaId);
        return mapAparatosToDTO(listAparatos);
    }

    public AparatoDTO getAparatoById(Long id) throws Exception {
        Optional<Aparato> aparato = repository.findById(id);
        if (aparato.isEmpty()) {
            throw new CSException("No se encontraron datos.");
        }

        SistemaDTO sistema = sistemaService.getSistemaById(aparato.get().getSistema().getId());
        return AparatoMapper.mapToDTO(sistema, aparato.get());
    }

    public Aparato createAparato(Aparato aparato, Long sistemaId) throws Exception {
        Sistema sistema = sistemaRepository.findById(sistemaId).orElseThrow(() -> new CSException("No se encontró el sistema con el ID: " + sistemaId));
        log.info("Aparato para %s.".formatted(sistema.getNombre()));
        aparato.setSistema(sistema);
        return repository.save(aparato);
    }

    public void deleteAparato(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteAparatoBySistemaId(Long id) {
        repository.deleteAllBySistemaId(id);
    }

    private List<AparatoDTO> mapAparatosToDTO(List<Aparato> listAparatos) throws Exception {
        if (listAparatos.isEmpty()) {
            throw new CSException("No se encontraron datos.");
        }

        List<AparatoDTO> listAparatosDTO = new ArrayList<>();
        for (Aparato aparato : listAparatos) {
            SistemaDTO sistema = sistemaService.getSistemaById(aparato.getSistema().getId());
            listAparatosDTO.add(AparatoMapper.mapToDTO(sistema, aparato));
        }

        return listAparatosDTO;
    }

}
