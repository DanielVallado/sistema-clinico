package com.clinica.controlreportes.dto.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HistorialClinicoDTO {

    private Long pacienteId;
    private String nombre;
    private String apellidos;
    private List<DiagnosticoDTO> diagnostico;
    private List<EstudiosDTO> estudios;
    private List<ExploracionFisicaDTO> exploracionFisica;
    private List<PronosticoDTO> pronostico;
    private List<RevaloracionDTO> revaloracion;

}
