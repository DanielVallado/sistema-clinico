package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.dto.DiagnosticoDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.dto.client.SistemaDTO;
import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.mapper.DiagnosticoMapper;
import com.clinica.controlhistorialclinico.model.Diagnostico;
import com.clinica.controlhistorialclinico.repository.DiagnosticoRepository;
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
public class DiagnosticoService {

    private DiagnosticoRepository repository;
    private Environment env;

    @Autowired
    private void setRepository(DiagnosticoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setEnv(Environment env) {
        this.env = env;
    }

    public List<DiagnosticoDTO> getAllDiagnosticos() throws Exception {
        List<Diagnostico> listDiagnosticos = repository.findAll();
        if (listDiagnosticos.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<DiagnosticoDTO> listDiagnosticosDTO = new ArrayList<>();
        for (Diagnostico diagnostico : listDiagnosticos) {
            PacienteDTO paciente = findPaciente(diagnostico.getPacienteId());
            listDiagnosticosDTO.add(DiagnosticoMapper.mapToDTO(paciente, diagnostico));
        }

        return listDiagnosticosDTO;
    }

    public List<DiagnosticoDTO> getDiagnosticosByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = findPaciente(id);

        List<Diagnostico> listDiagnosticos = repository.findAllByPacienteId(id);
        if (listDiagnosticos.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<DiagnosticoDTO> listDiagnosticosDTO = new ArrayList<>();
        for (Diagnostico diagnostico : listDiagnosticos) {
            listDiagnosticosDTO.add(DiagnosticoMapper.mapToDTO(paciente, diagnostico));
        }

        return listDiagnosticosDTO;
    }

    public Diagnostico createDiagnostico(Diagnostico diagnostico) {
        findPaciente(diagnostico.getPacienteId());
        findSistema(diagnostico.getSistemaId());
        return repository.save(diagnostico);
    }

    @Transactional
    public void deleteDiagnosticoByPacienteId(Long id) {
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

    private SistemaDTO findSistema(Long id) {
        return null;
    }

}
