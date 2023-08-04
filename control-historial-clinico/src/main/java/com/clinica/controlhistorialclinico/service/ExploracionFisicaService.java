package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.client.IPacienteClient;
import com.clinica.controlhistorialclinico.dto.ExploracionFisicaDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.mapper.ExploracionFisicaMapper;
import com.clinica.controlhistorialclinico.model.ExploracionFisica;
import com.clinica.controlhistorialclinico.repository.ExploracionFisicaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<ExploracionFisica> exploracionesFisicas = repository.findAll();
        if (exploracionesFisicas.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<ExploracionFisicaDTO> exploracionesDTO = new ArrayList<>();
        for (ExploracionFisica exploracion : exploracionesFisicas) {
            PacienteDTO paciente = pacienteClient.findByPacienteId(exploracion.getPacienteId()).getBody();

            if (paciente == null) {
                throw new CHCError("No se encontro al paciente.");
            }

            exploracionesDTO.add(ExploracionFisicaMapper.mapToDTO(paciente, exploracion));
        }

        return exploracionesDTO;
    }

    public List<ExploracionFisicaDTO> getExploracionesByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = pacienteClient.findByPacienteId(id).getBody();

        if (paciente == null) {
            throw new CHCError("No se encontro al paciente.");
        }

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

    public ExploracionFisica createExploracion(ExploracionFisica exploracionFisica) {
        pacienteClient.findByPacienteId(exploracionFisica.getPacienteId()).getBody();
        return repository.save(exploracionFisica);
    }

    @Transactional
    public void deleteExploracionByPacienteId(Long id) {
        repository.deleteAllByPacienteId(id);
    }

}
