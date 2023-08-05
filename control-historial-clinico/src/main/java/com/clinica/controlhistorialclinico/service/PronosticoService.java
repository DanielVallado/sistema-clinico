package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.client.IPacienteClient;
import com.clinica.controlhistorialclinico.dto.PronosticoDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.error.CHCException;
import com.clinica.controlhistorialclinico.mapper.PronosticoMapper;
import com.clinica.controlhistorialclinico.model.Pronostico;
import com.clinica.controlhistorialclinico.repository.PronosticoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PronosticoService {

    private PronosticoRepository repository;
    private IPacienteClient pacienteClient;

    @Autowired
    private void setRepository(PronosticoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setPacienteClient(IPacienteClient pacienteClient) {
        this.pacienteClient = pacienteClient;
    }

    public List<PronosticoDTO> getAllPronosticos() throws Exception {
        List<Pronostico> listPronosticos = repository.findAll();
        if (listPronosticos.isEmpty()) {
            throw new CHCException("No se encontraron datos.");
        }

        return listPronosticos.stream()
                .map(pronostico -> PronosticoMapper.mapToDTO(getPacienteById(pronostico.getPacienteId()), pronostico))
                .collect(Collectors.toList());
    }

    public List<PronosticoDTO> getPronosticosByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = pacienteClient.findPacienteById(id).getBody();
        if (paciente == null) {
            throw new CHCException("No se encontro al paciente.");
        }

        List<Pronostico> listPronosticos = repository.findAllByPacienteId(id);
        if (listPronosticos.isEmpty()) {
            throw new CHCException("No se encontraron datos.");
        }

        return listPronosticos.stream()
                .map(pronostico -> PronosticoMapper.mapToDTO(paciente, pronostico))
                .collect(Collectors.toList());
    }

    public Pronostico createPronostico(Pronostico pronostico) {
        pacienteClient.findPacienteById(pronostico.getPacienteId());
        return repository.save(pronostico);
    }

    public void deletePronostico(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deletePronosticoByPacienteId(Long pacienteId) {
        repository.deleteAllByPacienteId(pacienteId);
    }

    private PacienteDTO getPacienteById(Long pacienteId) {
        ResponseEntity<PacienteDTO> response = pacienteClient.findPacienteById(pacienteId);
        return response.getBody();
    }

}
