package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.dto.RevaloracionDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.mapper.RevaloracionMapper;
import com.clinica.controlhistorialclinico.model.Revaloracion;
import com.clinica.controlhistorialclinico.repository.RevaloracionRepository;
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
public class RevaloracionService {

    private RevaloracionRepository repository;
    private Environment env;

    @Autowired
    private void setRepository(RevaloracionRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setEnv(Environment env) {
        this.env = env;
    }

    public List<RevaloracionDTO> getAllRevaloraciones() throws Exception {
        List<Revaloracion> listRevaloraciones = repository.findAll();
        if (listRevaloraciones.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<RevaloracionDTO> listRevaloracionesDTO = new ArrayList<>();
        for (Revaloracion revaloracion : listRevaloraciones) {
            PacienteDTO paciente = findPaciente(revaloracion.getPacienteId());
            listRevaloracionesDTO.add(RevaloracionMapper.mapToDTO(paciente, revaloracion));
        }

        return listRevaloracionesDTO;
    }

    public List<RevaloracionDTO> getRevaloracionesByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = findPaciente(id);

        List<Revaloracion> listRevaloraciones = repository.findAllByPacienteId(id);
        if (listRevaloraciones.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<RevaloracionDTO> listRevaloracionesDTO = new ArrayList<>();
        for (Revaloracion revaloracion : listRevaloraciones) {
            listRevaloracionesDTO.add(RevaloracionMapper.mapToDTO(paciente, revaloracion));
        }

        return listRevaloracionesDTO;
    }

    public Revaloracion createRevaloracion(Revaloracion revaloracion) {
        findPaciente(revaloracion.getPacienteId());
        return repository.save(revaloracion);
    }

    @Transactional
    public void deleteRevaloracionByPacienteId(Long id) {
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
