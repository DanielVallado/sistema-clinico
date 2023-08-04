package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.client.IPacienteClient;
import com.clinica.controlhistorialclinico.dto.PronosticoDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.mapper.PronosticoMapper;
import com.clinica.controlhistorialclinico.model.Pronostico;
import com.clinica.controlhistorialclinico.repository.PronosticoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class PronosticoService {

    private PronosticoRepository repository;
    private IPacienteClient pacienteClient;

    @Autowired
    private void setRepository(PronosticoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setPacienteClient(IPacienteClient pacienteClient) {
        this.pacienteClient = pacienteClient;
    }

    public List<PronosticoDTO> getAllPronosticos() throws Exception {
        List<Pronostico> listPronosticos = repository.findAll();
        if (listPronosticos.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<PronosticoDTO> listPronosticosDTO = new ArrayList<>();
        for (Pronostico pronostico : listPronosticos) {
            PacienteDTO paciente = pacienteClient.findByPacienteId(pronostico.getPacienteId()).getBody();

            if (paciente == null) {
                throw new CHCError("No se encontro al paciente.");
            }

            listPronosticosDTO.add(PronosticoMapper.mapToDTO(paciente, pronostico));
        }

        return listPronosticosDTO;
    }

    public List<PronosticoDTO> getPronosticosByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = pacienteClient.findByPacienteId(id).getBody();
        if (paciente == null) {
            throw new CHCError("No se encontro al paciente.");
        }

        List<Pronostico> listPronosticos = repository.findAllByPacienteId(id);
        if (listPronosticos.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<PronosticoDTO> listPronosticosDTO = new ArrayList<>();
        for (Pronostico pronostico : listPronosticos) {
            listPronosticosDTO.add(PronosticoMapper.mapToDTO(paciente, pronostico));
        }

        return listPronosticosDTO;
    }

    public Pronostico createPronostico(Pronostico pronostico) {
        pacienteClient.findByPacienteId(pronostico.getPacienteId()).getBody();
        return repository.save(pronostico);
    }

    @Transactional
    public void deletePronosticoByPacienteId(Long id) {
        repository.deleteAllByPacienteId(id);
    }

    
}
