package com.clinica.controlcitas.mapper;

import com.clinica.controlcitas.dto.CitaDTO;
import com.clinica.controlcitas.dto.client.PacienteDTO;
import com.clinica.controlcitas.model.Cita;

public class CitaMapper {

    public static CitaDTO mapToDTO(PacienteDTO paciente, Cita cita) {
        CitaDTO dto = new CitaDTO();
        dto.setId(cita.getId());
        dto.setPacienteId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setApellidos(paciente.getApellidos());
        dto.setFechaHora(cita.getFechaHora());
        dto.setTipoPaciente(cita.getTipoPaciente());
        dto.setEstatusCita(cita.getEstatusCita());
        dto.setNoSesion(cita.getNoSesion());
        dto.setCostoTerapia(cita.getCostoTerapia());
        return dto;
    }

}
