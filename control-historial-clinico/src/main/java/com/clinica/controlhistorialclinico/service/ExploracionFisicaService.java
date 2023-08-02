package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.dto.ExploracionFisicaDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.mapper.ExploracionFisicaMapper;
import com.clinica.controlhistorialclinico.model.ExploracionFisica;
import com.clinica.controlhistorialclinico.repository.ExploracionFisicaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ExploracionFisicaService {

    @Autowired
    private ExploracionFisicaRepository repository;

    public List<ExploracionFisicaDTO> getAllExploraciones() throws Exception {
        List<ExploracionFisica> exploracionesFisicas = repository.findAll();
        if (exploracionesFisicas.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<ExploracionFisicaDTO> exploracionesDTO = new ArrayList<>();
        for (ExploracionFisica exploracion : exploracionesFisicas) {
            PacienteDTO paciente = findPaciente(exploracion.getPacienteId());
            exploracionesDTO.add(ExploracionFisicaMapper.mapToDTO(paciente, exploracion));
        }

        return exploracionesDTO;
    }

    public List<ExploracionFisicaDTO> getExploracionesByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = findPaciente(id);
        verificarPaciente(paciente);

        List<ExploracionFisica> exploracionesFisicas = repository.findAllByPacienteId(id);
        if (exploracionesFisicas.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<ExploracionFisicaDTO> exploracionesDTO = new ArrayList<>();
        for (ExploracionFisica exploracion : exploracionesFisicas) {
            exploracionesDTO.add(ExploracionFisicaMapper.mapToDTO(paciente, exploracion));
        }

        return exploracionesDTO;
    }

    public ExploracionFisica createExploracion(ExploracionFisica exploracionFisica) throws Exception {
        PacienteDTO paciente = findPaciente(exploracionFisica.getPacienteId());
        verificarPaciente(paciente);

        return repository.save(exploracionFisica);
    }

    public void deleteExploracionByPacienteId(Long id) {
        repository.deleteAllByPacienteId(id);
    }

    private PacienteDTO findPaciente(Long id) {
        return null;
    }

    private void verificarPaciente(PacienteDTO paciente) throws Exception {
        if (paciente == null) {
            throw new CHCError("No se encontro al paciente.");
        }
    }

}
