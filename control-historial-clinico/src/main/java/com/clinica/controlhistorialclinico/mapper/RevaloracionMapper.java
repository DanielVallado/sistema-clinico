package com.clinica.controlhistorialclinico.mapper;

import com.clinica.controlhistorialclinico.dto.RevaloracionDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.model.Revaloracion;

public class RevaloracionMapper {

    public static RevaloracionDTO mapToDTO(PacienteDTO paciente, Revaloracion revaloracion) {
        RevaloracionDTO dto = new RevaloracionDTO();
        dto.setPacienteId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellidos());
        dto.setDescripcion(revaloracion.getDescripcion());
        dto.setFechaRevaloracion(revaloracion.getFechaRevaloracion());
        return dto;
    }

}
