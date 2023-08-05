package com.clinica.controlhistorialclinico.mapper;

import com.clinica.controlhistorialclinico.dto.RevaloracionDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.dto.client.SistemaDTO;
import com.clinica.controlhistorialclinico.model.Revaloracion;

import java.util.List;
import java.util.stream.Collectors;

public class RevaloracionMapper {

    public static RevaloracionDTO mapToDTO(PacienteDTO paciente, SistemaDTO sistema, Revaloracion revaloracion) {
        RevaloracionDTO dto = new RevaloracionDTO();
        dto.setPacienteId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellidos());
        dto.setSistema(sistema.getNombre());
        dto.setDescripcion(revaloracion.getDescripcion());
        dto.setFechaRevaloracion(revaloracion.getFechaRevaloracion());
        return dto;
    }

    public static List<RevaloracionDTO> mapListToDTOList(List<Revaloracion> listRevaloraciones, PacienteDTO paciente, SistemaDTO sistema) {
        return listRevaloraciones.stream()
                .map(revaloracion -> mapToDTO(paciente, sistema, revaloracion))
                .collect(Collectors.toList());
    }

}
