package com.clinica.controlhistorialclinico.mapper;

import com.clinica.controlhistorialclinico.dto.DiagnosticoDTO;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.dto.client.SistemaDTO;
import com.clinica.controlhistorialclinico.model.Diagnostico;

import java.util.List;
import java.util.stream.Collectors;

public class DiagnosticoMapper {

    public static DiagnosticoDTO mapToDTO(PacienteDTO paciente, SistemaDTO sistema, Diagnostico diagnostico) {
        DiagnosticoDTO dto = new DiagnosticoDTO();
        dto.setPacienteId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellidos());
        dto.setSistema(sistema.getNombre());
        dto.setDescripcion(diagnostico.getDescripcion());
        dto.setFechaDiagnostico(diagnostico.getFechaDiagnostico());
        return dto;
    }

    public static List<DiagnosticoDTO> mapListToDTOList(List<Diagnostico> listDiagnosticos, PacienteDTO paciente, SistemaDTO sistema) {
        return listDiagnosticos.stream()
                .map(diagnostico -> mapToDTO(paciente, sistema, diagnostico))
                .collect(Collectors.toList());
    }

}
