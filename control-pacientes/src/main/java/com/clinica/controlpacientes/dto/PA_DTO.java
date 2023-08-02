package com.clinica.controlpacientes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PA_DTO {

    private String nombre;
    private String apellidos;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inicio;
    private String descripcion;
    private String evolucion;
    private String estadoActual;

}
