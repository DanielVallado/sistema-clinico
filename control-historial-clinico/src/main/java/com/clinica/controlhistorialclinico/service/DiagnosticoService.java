package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.client.IPacienteClient;
import com.clinica.controlhistorialclinico.dto.DiagnosticoDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.dto.client.SistemaDTO;
import com.clinica.controlhistorialclinico.error.CHCError;
import com.clinica.controlhistorialclinico.mapper.DiagnosticoMapper;
import com.clinica.controlhistorialclinico.model.Diagnostico;
import com.clinica.controlhistorialclinico.repository.DiagnosticoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class DiagnosticoService {

    private DiagnosticoRepository repository;
    private IPacienteClient pacienteClient;

    @Autowired
    private void setRepository(DiagnosticoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setPacienteClient(IPacienteClient pacienteClient) {
        this.pacienteClient = pacienteClient;
    }

    public List<DiagnosticoDTO> getAllDiagnosticos() throws Exception {
        List<Diagnostico> listDiagnosticos = repository.findAll();
        if (listDiagnosticos.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<DiagnosticoDTO> listDiagnosticosDTO = new ArrayList<>();
        for (Diagnostico diagnostico : listDiagnosticos) {
            PacienteDTO paciente = pacienteClient.findByPacienteId(diagnostico.getPacienteId()).getBody();

            if (paciente == null) {
                throw new CHCError("No se encontro al paciente.");
            }

            listDiagnosticosDTO.add(DiagnosticoMapper.mapToDTO(paciente, diagnostico));
        }

        return listDiagnosticosDTO;
    }

    public List<DiagnosticoDTO> getDiagnosticosByPacienteId(Long id) throws Exception {
        PacienteDTO paciente = pacienteClient.findByPacienteId(id).getBody();
        if (paciente == null) {
            throw new CHCError("No se encontro al paciente.");
        }

        List<Diagnostico> listDiagnosticos = repository.findAllByPacienteId(id);
        if (listDiagnosticos.isEmpty()) {
            throw new CHCError("No se encontraron datos.");
        }

        List<DiagnosticoDTO> listDiagnosticosDTO = new ArrayList<>();
        for (Diagnostico diagnostico : listDiagnosticos) {
            listDiagnosticosDTO.add(DiagnosticoMapper.mapToDTO(paciente, diagnostico));
        }

        return listDiagnosticosDTO;
    }

    public Diagnostico createDiagnostico(Diagnostico diagnostico) {
        pacienteClient.findByPacienteId(diagnostico.getPacienteId()).getBody();
        findSistema(diagnostico.getSistemaId());
        return repository.save(diagnostico);
    }

    @Transactional
    public void deleteDiagnosticoByPacienteId(Long id) {
        repository.deleteAllByPacienteId(id);
    }


    private SistemaDTO findSistema(Long id) {
        return null;
    }

}
