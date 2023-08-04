package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.client.IPacienteClient;
import com.clinica.controlhistorialclinico.dto.EstudiosDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.mapper.EstudiosMapper;
import com.clinica.controlhistorialclinico.model.Estudios;
import com.clinica.controlhistorialclinico.repository.EstudiosRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            throw new CHCError("No se encontraron datos.");
        }

        List<EstudiosDTO> listEstudiosDTO = new ArrayList<>();
        for (Estudios estudio : listEstudios) {
            PacienteDTO paciente = pacienteClient.findByPacienteId(estudio.getPacienteId()).getBody();

            if (paciente == null) {
                throw new CHCError("No se encontro al paciente.");
            }

            listEstudiosDTO.add(EstudiosMapper.mapToDTO(paciente, estudio));
        }

        return listEstudiosDTO;
    }

    public List<EstudiosDTO> getEstudiosByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = pacienteClient.findByPacienteId(id).getBody();
        if (paciente == null) {
            throw new CHCError("No se encontro al paciente.");
        }

        List<Estudios> listEstudios = repository.findAllByPacienteId(id);
        if (listEstudios.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<EstudiosDTO> listEstudiosDTO = new ArrayList<>();
        for (Estudios estudio : listEstudios) {
            listEstudiosDTO.add(EstudiosMapper.mapToDTO(paciente, estudio));
        }

        return listEstudiosDTO;
    }

    public Estudios createEstudios(Estudios estudio) {
        pacienteClient.findByPacienteId(estudio.getPacienteId()).getBody();
        return repository.save(estudio);
    }

    @Transactional
    public void deleteEstudiosByPacienteId(Long id) {
        repository.deleteAllByPacienteId(id);
    }
    
}
