package com.clinica.controlhistorialclinico.mapper;

import com.clinica.controlhistorialclinico.dto.PronosticoDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.model.Pronostico;

import java.util.List;
import java.util.stream.Collectors;

public class PronosticoMapper {

    public static PronosticoDTO mapToDTO(PacienteDTO paciente, Pronostico pronostico) {
        PronosticoDTO dto = new PronosticoDTO();
        dto.setPacienteId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellidos());
        dto.setDescripcion(pronostico.getDescripcion());
        dto.setFechaPronostico(pronostico.getFechaPronostico());
        return dto;
    }

    public static List<PronosticoDTO> mapListToDTOList(List<Pronostico> listPronosticos, PacienteDTO paciente) {
        return listPronosticos.stream()
                .map(pronostico -> mapToDTO(paciente, pronostico))
                .collect(Collectors.toList());
    }

}
