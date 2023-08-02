package com.clinica.controlpacientes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AHF_DTO {

    private String nombre;
    private String apellidos;
    private boolean cardiopatias;
    private boolean alergias;
    private boolean diabetes;
    private boolean nefropatias;
    private boolean psiquiatricos;
    private boolean neurologicas;
    private String otrosSindromes;

}
