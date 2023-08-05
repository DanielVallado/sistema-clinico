package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.client.IPacienteClient;
import com.clinica.controlhistorialclinico.dto.ExploracionFisicaDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.error.CHCException;
import com.clinica.controlhistorialclinico.mapper.ExploracionFisicaMapper;
import com.clinica.controlhistorialclinico.model.ExploracionFisica;
import com.clinica.controlhistorialclinico.repository.ExploracionFisicaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ExploracionFisicaService {

    private ExploracionFisicaRepository repository;
    private IPacienteClient pacienteClient;

    @Autowired
    private void setRepository(ExploracionFisicaRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setPacienteClient(IPacienteClient pacienteClient) {
        this.pacienteClient = pacienteClient;
    }

    public List<ExploracionFisicaDTO> getAllExploraciones() throws Exception {
        List<ExploracionFisica> listExploracionesFisicas = repository.findAll();
        if (listExploracionesFisicas.isEmpty()) {
            throw new CHCException("No se encontraron datos.");
        }

        return listExploracionesFisicas.stream()
                .map(exploracionFisica -> ExploracionFisicaMapper.mapToDTO(getPacienteById(exploracionFisica.getPacienteId()),exploracionFisica))
                .collect(Collectors.toList());
    }

    public List<ExploracionFisicaDTO> getExploracionesByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = pacienteClient.findPacienteById(id).getBody();
        if (paciente == null) {
            throw new CHCException("No se encontro al paciente.");
        }

        List<ExploracionFisica> listExploracionesFisicas = repository.findAllByPacienteId(id);
        if (listExploracionesFisicas.isEmpty()) {
            throw new CHCException("No se encontraron datos.");
        }

        return listExploracionesFisicas.stream()
                .map(exploracionFisica -> ExploracionFisicaMapper.mapToDTO(paciente,exploracionFisica))
                .collect(Collectors.toList());
    }

    public ExploracionFisica createExploracion(ExploracionFisica exploracionFisica) {
        pacienteClient.findPacienteById(exploracionFisica.getPacienteId());
        return repository.save(exploracionFisica);
    }

    public void deleteExploracion(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteExploracionByPacienteId(Long pacienteId) {
        repository.deleteAllByPacienteId(pacienteId);
    }

    private PacienteDTO getPacienteById(Long pacienteId) {
        ResponseEntity<PacienteDTO> response = pacienteClient.findPacienteById(pacienteId);
        return response.getBody();
    }

}
