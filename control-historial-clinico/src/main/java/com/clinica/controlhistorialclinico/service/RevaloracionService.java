package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.client.IPacienteClient;
import com.clinica.controlhistorialclinico.client.ISistemasClient;
import com.clinica.controlhistorialclinico.dto.RevaloracionDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.dto.client.SistemaDTO;
import com.clinica.controlhistorialclinico.error.CHCException;
import com.clinica.controlhistorialclinico.mapper.RevaloracionMapper;
import com.clinica.controlhistorialclinico.model.Revaloracion;
import com.clinica.controlhistorialclinico.repository.RevaloracionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class RevaloracionService {

    private RevaloracionRepository repository;
    private IPacienteClient pacienteClient;
    private ISistemasClient sistemasClient;

    @Autowired
    private void setRepository(RevaloracionRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setPacienteClient(IPacienteClient pacienteClient) {
        this.pacienteClient = pacienteClient;
    }

    @Autowired
    private void setSistemasClientClient(ISistemasClient sistemasClient) {
        this.sistemasClient = sistemasClient;
    }

    public List<RevaloracionDTO> getAllRevaloraciones() throws Exception {
        List<Revaloracion> listRevaloraciones = repository.findAll();
        if (listRevaloraciones.isEmpty()) {
            throw new CHCException("No se encontraron datos.");
        }

        return listRevaloraciones.stream()
                .map(estudio -> RevaloracionMapper.mapToDTO(getPacienteById(estudio.getPacienteId()), getSistemaById(estudio.getSistemaId()),estudio))
                .collect(Collectors.toList());
    }

    public List<RevaloracionDTO> getRevaloracionesByPacienteId(Long pacienteId) throws Exception {
        PacienteDTO paciente = pacienteClient.findPacienteById(pacienteId).getBody();
        if (paciente == null) {
            throw new CHCException("No se encontro al paciente.");
        }

        List<Revaloracion> listRevaloraciones = repository.findAllByPacienteId(pacienteId);
        if (listRevaloraciones.isEmpty()) {
            throw new CHCException("No se encontraron datos.");
        }

        return listRevaloraciones.stream()
                .map(estudio -> RevaloracionMapper.mapToDTO(paciente, getSistemaById(estudio.getSistemaId()),estudio))
                .collect(Collectors.toList());
    }

    public Revaloracion createRevaloracion(Revaloracion revaloracion) {
        pacienteClient.findPacienteById(revaloracion.getPacienteId());
        sistemasClient.findSistemaById(revaloracion.getSistemaId());
        return repository.save(revaloracion);
    }

    public void deleteRevaloracion(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteRevaloracionByPacienteId(Long pacienteId) {
        repository.deleteAllByPacienteId(pacienteId);
    }

    @Transactional
    public void deleteRevaloracionBySistemaId(Long sistemaId) {
        repository.deleteAllBySistemaId(sistemaId);
    }

    private PacienteDTO getPacienteById(Long pacienteId) {
        ResponseEntity<PacienteDTO> response = pacienteClient.findPacienteById(pacienteId);
        return response.getBody();
    }

    private SistemaDTO getSistemaById(Long sistemaId) {
        ResponseEntity<SistemaDTO> response = sistemasClient.findSistemaById(sistemaId);
        return response.getBody();
    }

}
