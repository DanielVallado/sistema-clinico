package com.clinica.controlreportes.dto.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PacienteDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creacionExpediente;
    private String nombre;
    private String apellidos;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private String direccion;
    private String telefono;
    private String email;
    private String estadoCivil;
    private String escolaridad;
    private String ocupacion;

}
