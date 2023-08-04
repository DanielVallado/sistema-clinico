package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.client.IPacienteClient;
import com.clinica.controlhistorialclinico.dto.RevaloracionDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.mapper.RevaloracionMapper;
import com.clinica.controlhistorialclinico.model.Revaloracion;
import com.clinica.controlhistorialclinico.repository.RevaloracionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class RevaloracionService {

    private RevaloracionRepository repository;
    private IPacienteClient pacienteClient;

    @Autowired
    private void setRepository(RevaloracionRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setPacienteClient(IPacienteClient pacienteClient) {
        this.pacienteClient = pacienteClient;
    }

    public List<RevaloracionDTO> getAllRevaloraciones() throws Exception {
        List<Revaloracion> listRevaloraciones = repository.findAll();
        if (listRevaloraciones.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<RevaloracionDTO> listRevaloracionesDTO = new ArrayList<>();
        for (Revaloracion revaloracion : listRevaloraciones) {
            PacienteDTO paciente = pacienteClient.findByPacienteId(revaloracion.getPacienteId()).getBody();

            if (paciente == null) {
                throw new CHCError("No se encontro al paciente.");
            }

            listRevaloracionesDTO.add(RevaloracionMapper.mapToDTO(paciente, revaloracion));
        }

        return listRevaloracionesDTO;
    }

    public List<RevaloracionDTO> getRevaloracionesByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = pacienteClient.findByPacienteId(id).getBody();
        if (paciente == null) {
            throw new CHCError("No se encontro al paciente.");
        }

        List<Revaloracion> listRevaloraciones = repository.findAllByPacienteId(id);
        if (listRevaloraciones.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<RevaloracionDTO> listRevaloracionesDTO = new ArrayList<>();
        for (Revaloracion revaloracion : listRevaloraciones) {
            listRevaloracionesDTO.add(RevaloracionMapper.mapToDTO(paciente, revaloracion));
        }

        return listRevaloracionesDTO;
    }

    public Revaloracion createRevaloracion(Revaloracion revaloracion) {
        pacienteClient.findByPacienteId(revaloracion.getPacienteId()).getBody();
        return repository.save(revaloracion);
    }

    @Transactional
    public void deleteRevaloracionByPacienteId(Long id) {
        repository.deleteAllByPacienteId(id);
    }
    
}
