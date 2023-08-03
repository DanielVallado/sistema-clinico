package com.clinica.controlhistorialclinico.mapper;

import com.clinica.controlhistorialclinico.dto.ExploracionFisicaDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.model.ExploracionFisica;

public class ExploracionFisicaMapper {

    public static ExploracionFisicaDTO mapToDTO(PacienteDTO paciente, ExploracionFisica exploracionFisica) {
        ExploracionFisicaDTO dto = new ExploracionFisicaDTO();
        dto.setPacienteId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellidos());
        dto.setDescripcion(exploracionFisica.getDescripcion());
        dto.setFechaExploracion(exploracionFisica.getFechaExploracion());
        return dto;
    }

}
