package com.clinica.controlcitas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CitaDTO {

    private Long id;
    private Long pacienteId;
    private String nombre;
    private String apellidos;
    private LocalDateTime fechaHora;
    private String tipoPaciente;
    private String estatusCita;
    private int noSesion;
    private BigDecimal costoTerapia;

}
