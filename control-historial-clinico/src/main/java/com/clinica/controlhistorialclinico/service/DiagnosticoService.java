package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.client.IPacienteClient;
import com.clinica.controlhistorialclinico.client.ISistemasClient;
import com.clinica.controlhistorialclinico.dto.DiagnosticoDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.dto.client.SistemaDTO;
import com.clinica.controlhistorialclinico.error.CHCException;
import com.clinica.controlhistorialclinico.mapper.DiagnosticoMapper;
import com.clinica.controlhistorialclinico.model.Diagnostico;
import com.clinica.controlhistorialclinico.repository.DiagnosticoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class DiagnosticoService {

    private DiagnosticoRepository repository;
    private IPacienteClient pacienteClient;
    private ISistemasClient sistemasClient;

    @Autowired
    private void setRepository(DiagnosticoRepository repository) {
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

    public List<DiagnosticoDTO> getAllDiagnosticos() throws Exception {
        List<Diagnostico> listDiagnosticos = repository.findAll();
        if (listDiagnosticos.isEmpty()) {
            throw new CHCException("No se encontraron datos.");
        }

        return listDiagnosticos.stream()
                .map(diagnostico -> DiagnosticoMapper.mapToDTO(getPacienteById(diagnostico.getPacienteId()), getSistemaById(diagnostico.getSistemaId()),diagnostico))
                .collect(Collectors.toList());
    }

    public List<DiagnosticoDTO> getDiagnosticosByPacienteId(Long pacienteId) throws Exception {
        PacienteDTO paciente = pacienteClient.findPacienteById(pacienteId).getBody();
        if (paciente == null) {
            throw new CHCException("No se encontro al paciente.");
        }

        List<Diagnostico> listDiagnosticos = repository.findAllByPacienteId(pacienteId);
        if (listDiagnosticos.isEmpty()) {
            throw new CHCException("No se encontraron datos.");
        }

        return listDiagnosticos.stream()
                .map(diagnostico -> DiagnosticoMapper.mapToDTO(paciente, getSistemaById(diagnostico.getSistemaId()),diagnostico))
                .collect(Collectors.toList());
    }

    public Diagnostico createDiagnostico(Diagnostico diagnostico) {
        pacienteClient.findPacienteById(diagnostico.getPacienteId());
        sistemasClient.findSistemaById(diagnostico.getSistemaId());
        return repository.save(diagnostico);
    }

    public void deleteDiagnostico(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteDiagnosticoByPacienteId(Long pacienteId) {
        repository.deleteAllByPacienteId(pacienteId);
    }

    @Transactional
    public void deleteDiagnosticoBySistemaId(Long sistemaId) {
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
