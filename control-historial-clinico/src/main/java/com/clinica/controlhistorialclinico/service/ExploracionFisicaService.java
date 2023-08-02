package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.dto.ExploracionFisicaDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.mapper.ExploracionFisicaMapper;
import com.clinica.controlhistorialclinico.model.ExploracionFisica;
import com.clinica.controlhistorialclinico.repository.ExploracionFisicaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class ExploracionFisicaService {

    private ExploracionFisicaRepository repository;
    private Environment env;

    @Autowired
    private void setRepository(ExploracionFisicaRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setEnv(Environment env) {
        this.env = env;
    }

    public List<ExploracionFisicaDTO> getAllExploraciones() throws Exception {
        List<ExploracionFisica> exploracionesFisicas = repository.findAll();
        if (exploracionesFisicas.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<ExploracionFisicaDTO> exploracionesDTO = new ArrayList<>();
        for (ExploracionFisica exploracion : exploracionesFisicas) {
            PacienteDTO paciente = findPaciente(exploracion.getPacienteId());
            exploracionesDTO.add(ExploracionFisicaMapper.mapToDTO(paciente, exploracion));
        }

        return exploracionesDTO;
    }

    public List<ExploracionFisicaDTO> getExploracionesByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = findPaciente(id);

        List<ExploracionFisica> exploracionesFisicas = repository.findAllByPacienteId(id);
        if (exploracionesFisicas.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<ExploracionFisicaDTO> exploracionesDTO = new ArrayList<>();
        for (ExploracionFisica exploracion : exploracionesFisicas) {
            exploracionesDTO.add(ExploracionFisicaMapper.mapToDTO(paciente, exploracion));
        }

        return exploracionesDTO;
    }

    public ExploracionFisica createExploracion(ExploracionFisica exploracionFisica) {
        findPaciente(exploracionFisica.getPacienteId());
        return repository.save(exploracionFisica);
    }

    @Transactional
    public void deleteExploracionByPacienteId(Long id) {
        repository.deleteAllByPacienteId(id);
    }

    private PacienteDTO findPaciente(Long id) {
        String url = env.getProperty("URL_CP") + id;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        log.info("Busqueda de paciente con id " + id);
        ResponseEntity<PacienteDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, PacienteDTO.class);

        return response.getBody();
    }

}
