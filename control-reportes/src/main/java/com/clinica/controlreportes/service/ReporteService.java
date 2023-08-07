package com.clinica.controlreportes.service;

import com.clinica.controlreportes.client.ICitasClient;
import com.clinica.controlreportes.client.IHistorialClinicoClient;
import com.clinica.controlreportes.client.IPacienteClient;
import com.clinica.controlreportes.dto.client.CitaDTO;
import com.clinica.controlreportes.dto.client.HistorialClinicoDTO;
import com.clinica.controlreportes.dto.client.PacienteDTO;
import com.clinica.controlreportes.enums.EstatusCita;
import com.clinica.controlreportes.enums.TipoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    private IPacienteClient pacienteClient;
    private IHistorialClinicoClient historialClinicoClient;
    private ICitasClient citasClient;

    @Autowired
    private void setPacienteClient(IPacienteClient pacienteClient) {
        this.pacienteClient = pacienteClient;
    }

    @Autowired
    private void setHistorialClinicoClient(IHistorialClinicoClient historialClinicoClient) {
        this.historialClinicoClient = historialClinicoClient;
    }

    @Autowired
    private void setCitasClient(ICitasClient citasClient) {
        this.citasClient = citasClient;
    }

    public HistorialClinicoDTO getHistorialClinicoByPacienteId(Long pacienteId) {
        return historialClinicoClient.getHistorialClinicoByPacienteId(pacienteId).getBody();
    }

    public List<CitaDTO> getCitas(Date fechaInicial, Date fechaFinal) {
        List<CitaDTO> citas = citasClient.getAllCitas();
        return filterCitasByDate(fechaInicial, fechaFinal, citas);
    }

    public List<CitaDTO> getCitasCanceladas(Date fechaInicial, Date fechaFinal) {
        List<CitaDTO> citas = citasClient.getAllCitas();
        return filterByDateAndStatus(fechaInicial, fechaFinal, citas);
    }

    public List<PacienteDTO> getPacientesSubsecuentes(Date fechaInicial, Date fechaFinal) {
        List<CitaDTO> listCitas = citasClient.getAllCitas();
        return filterPacientesSubsecuentes(fechaInicial, fechaFinal, listCitas);
    }

    private List<CitaDTO> filterCitasByDate(Date fechaInicial, Date fechaFinal, List<CitaDTO> citas) {
        return citas.stream()
                .filter(cita -> isDateInRange(cita.getFechaHora(), fechaInicial, fechaFinal))
                .collect(Collectors.toList());
    }

    private List<CitaDTO> filterByDateAndStatus(Date fechaInicial, Date fechaFinal, List<CitaDTO> citas) {
        return citas.stream()
                .filter(cita -> {
                    LocalDateTime citaDate = cita.getFechaHora();
                    Instant instant = citaDate.toInstant(ZoneOffset.UTC);
                    Date date = Date.from(instant);
                    return !date.before(fechaInicial) && !date.after(fechaFinal)
                            && cita.getEstatusCita().equals(EstatusCita.CANCELADA);
                })
                .collect(Collectors.toList());
    }

    private List<PacienteDTO> filterPacientesSubsecuentes(Date fechaInicial, Date fechaFinal, List<CitaDTO> citas) {
        Set<Long> uniquePacienteIds = new HashSet<>();

        return citas.stream()
                .filter(cita -> cita.getTipoPaciente().equals(TipoPaciente.SUBSECUENTE))
                .filter(cita -> isDateInRange(cita.getFechaHora(), fechaInicial, fechaFinal))
                .filter(cita -> uniquePacienteIds.add(cita.getPacienteId()))
                .map(cita -> pacienteClient.findPacienteById(cita.getPacienteId()).getBody())
                .collect(Collectors.toList());
    }


    private boolean isDateInRange(LocalDateTime dateTime, Date fechaInicial, Date fechaFinal) {
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Date date = Date.from(instant);
        return !date.before(fechaInicial) && !date.after(fechaFinal);
    }

}
