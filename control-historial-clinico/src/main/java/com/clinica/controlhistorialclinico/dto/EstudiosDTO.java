package com.clinica.controlhistorialclinico.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstudiosDTO {

    private Long pacienteId;
    private String nombre;
    private String apellido;
    private String url;

}
