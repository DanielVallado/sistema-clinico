package com.clinica.controlhistorialclinico.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class DiagnosticoDTO {

    private Long pacienteId;
    private String nombre;
    private String apellido;
    private Long sistemaId;
    private String descripcion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaDiagnostico;

}
