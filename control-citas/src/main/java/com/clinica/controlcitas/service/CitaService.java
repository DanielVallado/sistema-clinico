package com.clinica.controlcitas.service;

import com.clinica.controlcitas.client.IPacienteClient;
import com.clinica.controlcitas.dto.CitaDTO;
import com.clinica.controlcitas.dto.client.PacienteDTO;
import com.clinica.controlcitas.error.CCException;
import com.clinica.controlcitas.mapper.CitaMapper;
import com.clinica.controlcitas.model.Cita;
import com.clinica.controlcitas.repository.CitaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class CitaService {

    private CitaRepository repository;
    private IPacienteClient pacienteClient;

    @Autowired
    private void setRepository(CitaRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setCitaClient(IPacienteClient pacienteClient) {
        this.pacienteClient = pacienteClient;
    }

    public List<CitaDTO> getAllCitas() throws Exception {
        List<Cita> listCitas = repository.findAll();
        if (listCitas.isEmpty()) {
            throw new CCException("No se encontraron datos.");
        }

        List<CitaDTO> listCitasDTO = new ArrayList<>();
        for (Cita cita : listCitas) {
            PacienteDTO paciente = pacienteClient.findByPacienteId(cita.getPacienteId()).getBody();

            if (paciente == null) {
                throw new CCException("No se encontro al paciente.");
            }

            listCitasDTO.add(CitaMapper.mapToDTO(paciente, cita));
        }

        return listCitasDTO;
    }

    public List<CitaDTO> getCitasByPacienteId(Long pacienteId) throws Exception {
        PacienteDTO paciente = pacienteClient.findByPacienteId(pacienteId).getBody();
        if (paciente == null) {
            throw new CCException("No se encontro al paciente.");
        }

        List<Cita> citas = repository.findAllByPacienteId(pacienteId);
        if (citas.isEmpty()) {
            throw new CCException("No se encontraron datos.");
        }

        List<CitaDTO> citasDTO = new ArrayList<>();
        for (Cita cita : citas) {
            citasDTO.add(CitaMapper.mapToDTO(paciente, cita));
        }

        return citasDTO;
    }

    public Cita createCita(Cita cita) {
        pacienteClient.findByPacienteId(cita.getPacienteId());
        return repository.save(cita);
    }

    public void deleteCita(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteCitaByPacienteId(Long pacienteId) {
        repository.deleteAllByPacienteId(pacienteId);
    }

}
