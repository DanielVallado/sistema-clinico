package com.clinica.controlhistorialclinico.mapper;

import com.clinica.controlhistorialclinico.dto.*;
import com.clinica.controlhistorialclinico.dto.client.PacienteDTO;
import com.clinica.controlhistorialclinico.dto.client.SistemaDTO;
import com.clinica.controlhistorialclinico.model.Diagnostico;

import java.util.List;

public class HistorialClinicoMapper {

    public static HistorialClinicoDTO mapToDTO(PacienteDTO paciente, List<DiagnosticoDTO> listDiagnosticos,
                                               List<EstudiosDTO> listEstudios,
                                               List<ExploracionFisicaDTO> listExploraciones,
                                               List<PronosticoDTO> listPronosticos,
                                               List<RevaloracionDTO> listRevaloraciones) {
        HistorialClinicoDTO dto = new HistorialClinicoDTO();
        dto.setPacienteId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setApellidos(paciente.getApellidos());
        dto.setDiagnostico(listDiagnosticos);
        dto.setEstudios(listEstudios);
        dto.setExploracionFisica(listExploraciones);
        dto.setPronostico(listPronosticos);
        dto.setRevaloracion(listRevaloraciones);
        return dto;
    }

}
