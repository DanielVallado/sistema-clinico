package com.clinica.controlhistorialclinico.service;

import com.clinica.controlhistorialclinico.client.IPacienteClient;
import com.clinica.controlhistorialclinico.dto.*;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.error.CHCException;
import com.clinica.controlhistorialclinico.mapper.HistorialClinicoMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Log4j2
public class HistorialClinicoService {

    private IPacienteClient pacienteClient;
    private DiagnosticoService diagnosticoService;
    private EstudiosService estudiosService;
    private ExploracionFisicaService exploracionFisicaService;
    private PronosticoService pronosticoService;
    private RevaloracionService revaloracionService;

    @Autowired
    private void setPacienteClient(IPacienteClient pacienteClient, DiagnosticoService diagnosticoService,
                                   EstudiosService estudiosService, ExploracionFisicaService exploracionFisicaService,
                                   PronosticoService pronosticoService, RevaloracionService revaloracionService) {
        this.pacienteClient = pacienteClient;
        this.diagnosticoService = diagnosticoService;
        this.estudiosService = estudiosService;
        this.exploracionFisicaService = exploracionFisicaService;
        this.pronosticoService = pronosticoService;
        this.revaloracionService = revaloracionService;
    }

    public List<HistorialClinicoDTO> getAllHistorialesClinicos() throws Exception {
        List<PacienteDTO> listPacientes = pacienteClient.findAllPacientes();
        List<HistorialClinicoDTO> listHistoriales = new ArrayList<>();
        for (PacienteDTO paciente : listPacientes) {
            listHistoriales.add(getHistorialClinicoByPacienteId(paciente.getId()));
        }

        return listHistoriales;
    }

    public HistorialClinicoDTO getHistorialClinicoByPacienteId(Long pacienteId) throws Exception {
        PacienteDTO paciente = pacienteClient.findPacienteById(pacienteId).getBody();
        if (paciente == null) {
            throw new CHCException("No se encontro al paciente.");
        }

        List<DiagnosticoDTO> listDiagnosticos = findDiagnosticos(pacienteId);
        List<EstudiosDTO> listEstudios = findEstudios(pacienteId);
        List<ExploracionFisicaDTO> listExploraciones = findExploraciones(pacienteId);
        List<PronosticoDTO> listPronosticos = findPronosticos(pacienteId);
        List<RevaloracionDTO> listRevaloraciones = findRevaloraciones(pacienteId);

        return HistorialClinicoMapper.mapToDTO(paciente, listDiagnosticos, listEstudios, listExploraciones,
                listPronosticos, listRevaloraciones);
    }

    private List<DiagnosticoDTO> findDiagnosticos(Long pacienteId) {
        try {
            return diagnosticoService.getDiagnosticosByPacienteId(pacienteId);
        } catch (Exception e) {
            return null;
        }
    }

    private List<EstudiosDTO> findEstudios(Long pacienteId) {
        try {
            return estudiosService.getEstudiosByPacienteId(pacienteId);
        } catch (Exception e) {
            return null;
        }
    }

    private List<ExploracionFisicaDTO> findExploraciones(Long pacienteId) {
        try {
            return exploracionFisicaService.getExploracionesByPacienteId(pacienteId);
        } catch (Exception e) {
            return null;
        }
    }

    private List<PronosticoDTO> findPronosticos(Long pacienteId) {
        try {
            return pronosticoService.getPronosticosByPacienteId(pacienteId);
        } catch (Exception e) {
            return null;
        }
    }

    private List<RevaloracionDTO> findRevaloraciones(Long pacienteId) {
        try {
            return revaloracionService.getRevaloracionesByPacienteId(pacienteId);
        } catch (Exception e) {
            return null;
        }
    }


    public void deleteHistorialClinicoByPacienteId(Long pacienteId) {
        diagnosticoService.deleteDiagnosticoByPacienteId(pacienteId);
        estudiosService.deleteEstudiosByPacienteId(pacienteId);
        exploracionFisicaService.deleteExploracionByPacienteId(pacienteId);
        pronosticoService.deletePronosticoByPacienteId(pacienteId);
        revaloracionService.deleteRevaloracion(pacienteId);
    }

    public void deleteHistorialClinicoBySistemaId(Long sistemaId) {
        diagnosticoService.deleteDiagnosticoBySistemaId(sistemaId);
        revaloracionService.deleteRevaloracionBySistemaId(sistemaId);
    }

}
