package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.dto.PronosticoDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.mapper.PronosticoMapper;
import com.clinica.controlhistorialclinico.model.Pronostico;
import com.clinica.controlhistorialclinico.repository.PronosticoRepository;
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
public class PronosticoService {

    private PronosticoRepository repository;
    private Environment env;

    @Autowired
    private void setRepository(PronosticoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setEnv(Environment env) {
        this.env = env;
    }

    public List<PronosticoDTO> getAllPronosticos() throws Exception {
        List<Pronostico> listPronosticos = repository.findAll();
        if (listPronosticos.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<PronosticoDTO> listPronosticosDTO = new ArrayList<>();
        for (Pronostico pronostico : listPronosticos) {
            PacienteDTO paciente = findPaciente(pronostico.getPacienteId());
            listPronosticosDTO.add(PronosticoMapper.mapToDTO(paciente, pronostico));
        }

        return listPronosticosDTO;
    }

    public List<PronosticoDTO> getPronosticosByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = findPaciente(id);

        List<Pronostico> listPronosticos = repository.findAllByPacienteId(id);
        if (listPronosticos.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<PronosticoDTO> listPronosticosDTO = new ArrayList<>();
        for (Pronostico pronostico : listPronosticos) {
            listPronosticosDTO.add(PronosticoMapper.mapToDTO(paciente, pronostico));
        }

        return listPronosticosDTO;
    }

    public Pronostico createPronostico(Pronostico pronostico) {
        findPaciente(pronostico.getPacienteId());
        return repository.save(pronostico);
    }

    @Transactional
    public void deletePronosticoByPacienteId(Long id) {
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
