package com.clinica.controlsistemas.mapper;

import com.clinica.controlsistemas.dto.SistemaDTO;
import com.clinica.controlsistemas.model.Sistema;

public class SistemaMapper {

    public static SistemaDTO mapToDTO(Sistema sistema) {
        SistemaDTO dto = new SistemaDTO();
        dto.setId(sistema.getId());
        dto.setNombre(sistema.getNombre());
        dto.setDescripcion(sistema.getDescripcion());
        return dto;
    }

}
