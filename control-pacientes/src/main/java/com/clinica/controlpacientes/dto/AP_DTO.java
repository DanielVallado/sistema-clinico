package com.clinica.controlpacientes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AP_DTO {

    private String nombre;
    private String apellidos;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private int sdg;
    private int apgar;
    private float peso;
    private float talla;
    private int numeroEmbarazo;
    private String problemasDuranteParto;

}
