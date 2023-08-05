package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.client.IPacienteClient;
import com.clinica.controlhistorialclinico.dto.EstudiosDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.error.CHCException;
import com.clinica.controlhistorialclinico.mapper.EstudiosMapper;
import com.clinica.controlhistorialclinico.model.Estudios;
import com.clinica.controlhistorialclinico.repository.EstudiosRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class EstudiosService {

    private EstudiosRepository repository;
    private IPacienteClient pacienteClient;

    @Autowired
    private void setRepository(EstudiosRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setPacienteClient(IPacienteClient pacienteClient) {
        this.pacienteClient = pacienteClient;
    }

    public List<EstudiosDTO> getAllEstudios() throws Exception {
        List<Estudios> listEstudios = repository.findAll();
        if (listEstudios.isEmpty()) {
            throw new CHCException("No se encontraron datos.");
        }

        return listEstudios.stream()
                .map(estudio -> EstudiosMapper.mapToDTO(getPacienteById(estudio.getPacienteId()), estudio))
                .collect(Collectors.toList());
    }

    public List<EstudiosDTO> getEstudiosByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = getPacienteById(id);

        List<Estudios> listEstudios = repository.findAllByPacienteId(id);
        if (listEstudios.isEmpty()) {
            throw new CHCException("No se encontraron datos.");
        }

        return listEstudios.stream()
                .map(estudio -> EstudiosMapper.mapToDTO(paciente, estudio))
                .collect(Collectors.toList());
    }

    public Estudios createEstudios(Estudios estudio) {
        pacienteClient.findPacienteById(estudio.getPacienteId());
        return repository.save(estudio);
    }

    public void deleteEstudios(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteEstudiosByPacienteId(Long pacienteId) {
        repository.deleteAllByPacienteId(pacienteId);
    }

    private PacienteDTO getPacienteById(Long pacienteId) {
        ResponseEntity<PacienteDTO> response = pacienteClient.findPacienteById(pacienteId);
        return response.getBody();
    }
    
}
