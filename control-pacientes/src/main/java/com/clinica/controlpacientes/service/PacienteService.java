package com.clinica.controlpacientes.service;

import com.clinica.controlpacientes.dto.PacienteDTO;
import com.clinica.controlpacientes.error.CPException;
import com.clinica.controlpacientes.mapper.PacienteMapper;
import com.clinica.controlpacientes.model.Paciente;
import com.clinica.controlpacientes.repository.PacienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

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

        List<PacienteDTO> pacientesDTO = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            pacientesDTO.add(PacienteMapper.mapToDTO(paciente));
        }

        return pacientesDTO;
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
    }

}
