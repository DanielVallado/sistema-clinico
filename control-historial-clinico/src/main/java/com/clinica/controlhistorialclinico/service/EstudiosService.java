package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.dto.EstudiosDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.mapper.EstudiosMapper;
import com.clinica.controlhistorialclinico.model.Estudios;
import com.clinica.controlhistorialclinico.repository.EstudiosRepository;
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
public class EstudiosService {

    private EstudiosRepository repository;
    private Environment env;

    @Autowired
    private void setRepository(EstudiosRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setEnv(Environment env) {
        this.env = env;
    }

    public List<EstudiosDTO> getAllEstudios() throws Exception {
        List<Estudios> listEstudios = repository.findAll();
        if (listEstudios.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<EstudiosDTO> listEstudiosDTO = new ArrayList<>();
        for (Estudios estudio : listEstudios) {
            PacienteDTO paciente = findPaciente(estudio.getPacienteId());
            listEstudiosDTO.add(EstudiosMapper.mapToDTO(paciente, estudio));
        }

        return listEstudiosDTO;
    }

    public List<EstudiosDTO> getEstudiosByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = findPaciente(id);

        List<Estudios> listEstudios = repository.findAllByPacienteId(id);
        if (listEstudios.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<EstudiosDTO> listEstudiosDTO = new ArrayList<>();
        for (Estudios estudio : listEstudios) {
            listEstudiosDTO.add(EstudiosMapper.mapToDTO(paciente, estudio));
        }

        return listEstudiosDTO;
    }

    public Estudios createEstudios(Estudios estudio) {
        findPaciente(estudio.getPacienteId());
        return repository.save(estudio);
    }

    @Transactional
    public void deleteEstudiosByPacienteId(Long id) {
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
