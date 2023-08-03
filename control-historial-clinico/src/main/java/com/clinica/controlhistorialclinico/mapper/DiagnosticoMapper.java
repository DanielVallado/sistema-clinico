package com.clinica.controlhistorialclinico.mapper;

import com.clinica.controlhistorialclinico.dto.DiagnosticoDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.model.Diagnostico;

public class DiagnosticoMapper {

    public static DiagnosticoDTO mapToDTO(PacienteDTO paciente, Diagnostico diagnostico) {
        DiagnosticoDTO dto = new DiagnosticoDTO();
        dto.setPacienteId(paciente.getId());
        dto.setSistemaId(diagnostico.getSistemaId());
        dto.setDescripcion(diagnostico.getDescripcion());
        dto.setFechaDiagnostico(diagnostico.getFechaDiagnostico());
        return dto;
    }

}
