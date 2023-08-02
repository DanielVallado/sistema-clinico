package com.clinica.controlpacientes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AGO_DTO {

    private String nombre;
    private String apellidos;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date menarca;
    private String ritmoMenstrual;
    private int ipsa;
    private int partos;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fup;
    private int abortos;
    private int cesareas;

}
