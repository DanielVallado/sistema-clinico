package com.clinica.controlhistorialclinico.mapper;

import com.clinica.controlhistorialclinico.dto.EstudiosDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.model.Estudios;

import java.util.List;
import java.util.stream.Collectors;

public class EstudiosMapper {

    public static EstudiosDTO mapToDTO(PacienteDTO paciente, Estudios estudios) {
        EstudiosDTO dto = new EstudiosDTO();
        dto.setPacienteId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellidos());
        dto.setUrl(estudios.getUrl());
        return dto;
    }

    public static List<EstudiosDTO> mapListToDTOList(List<Estudios> listEstudios, PacienteDTO paciente) {
        return listEstudios.stream()
                .map(estudio -> mapToDTO(paciente, estudio))
                .collect(Collectors.toList());
    }

}
