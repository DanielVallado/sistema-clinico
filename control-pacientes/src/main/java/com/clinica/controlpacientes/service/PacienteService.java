package com.clinica.controlpacientes.service;

import com.clinica.controlpacientes.client.ICitasClient;
import com.clinica.controlpacientes.client.IHistorialClinicoClient;
import com.clinica.controlpacientes.dto.PacienteDTO;
import com.clinica.controlpacientes.error.CPException;
import com.clinica.controlpacientes.mapper.PacienteMapper;
import com.clinica.controlpacientes.model.Paciente;
import com.clinica.controlpacientes.repository.PacienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PacienteService {

    private PacienteRepository repository;
    private IHistorialClinicoClient historialClinicoClient;
    private ICitasClient citasClient;

    @Autowired
    private void setRepository(PacienteRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setHistorialClinicoClient(IHistorialClinicoClient historialClinicoClient) {
        this.historialClinicoClient = historialClinicoClient;
    }

    @Autowired
    private void setCitasClient(ICitasClient citasClient) {
        this.citasClient = citasClient;
    }

    public List<Paciente> getAllPacientes() throws Exception {
        List<Paciente> pacientes = repository.findAll();
        if (pacientes.isEmpty()) {
            throw new CPException("No se encontraron datos.");
        }
        return pacientes;
    }

    public List<PacienteDTO> getAllPacientesDto() throws Exception {
        List<Paciente> pacientes = repository.findAll();
        if (pacientes.isEmpty()) {
            throw new CPException("No se encontraron datos.");
        }

        return pacientes.stream().map(PacienteMapper::mapToDTO).collect(Collectors.toList());
    }

    public Paciente getPacienteById(Long id) throws Exception {
        Optional<Paciente> paciente = repository.findById(id);
        return paciente.orElseThrow(() -> new CPException("No se encontraron datos"));
    }

    public PacienteDTO getPacienteDtoById(Long id) throws Exception {
        Optional<Paciente> paciente = repository.findById(id);
        if (paciente.isEmpty()) {
            throw new CPException("No se encontraron datos.");
        }
        return PacienteMapper.mapToDTO(paciente.get());
    }

    public Paciente createPaciente(Paciente paciente) {
        return repository.save(paciente);
    }

    public void deletePaciente(Long id) {
        repository.deleteById(id);
        historialClinicoClient.deleteHistorialClinicoByPacienteId(id);
        citasClient.deleteCitaByPacienteId(id);
    }

}
