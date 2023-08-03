package com.clinica.controlhistorialclinico.mapper;

import com.clinica.controlhistorialclinico.dto.EstudiosDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.model.Estudios;

public class EstudiosMapper {

    public static EstudiosDTO mapToDTO(PacienteDTO paciente, Estudios estudios) {
        EstudiosDTO dto = new EstudiosDTO();
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellidos());
        dto.setUrl(estudios.getUrl());
        return dto;
    }

}
