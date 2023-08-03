package com.clinica.controlhistorialclinico.mapper;

import com.clinica.controlhistorialclinico.dto.PronosticoDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.model.Pronostico;

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


}
