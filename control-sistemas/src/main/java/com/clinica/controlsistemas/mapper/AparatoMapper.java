package com.clinica.controlsistemas.mapper;

import com.clinica.controlsistemas.dto.AparatoDTO;
import com.clinica.controlsistemas.dto.SistemaDTO;
import com.clinica.controlsistemas.model.Aparato;

public class AparatoMapper {

    public static AparatoDTO mapToDTO(SistemaDTO sistema, Aparato aparato) {
        AparatoDTO dto = new AparatoDTO();
        dto.setId(aparato.getId());
        dto.setSistema(sistema.getNombre());
        dto.setNombre(aparato.getNombre());
        dto.setDescripcion(aparato.getDescripcion());
        return dto;
    }

}
